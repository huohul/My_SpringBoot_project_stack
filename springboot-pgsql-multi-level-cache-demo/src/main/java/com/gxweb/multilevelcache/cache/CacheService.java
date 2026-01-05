package com.gxweb.multilevelcache.cache;

import org.springframework.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Service
public class CacheService {

    private final MultiLevelCacheManager cacheManager;
    private final RedisTemplate<String, Object> redisTemplate;

    public CacheService(MultiLevelCacheManager cacheManager, RedisTemplate<String, Object> redisTemplate) {
        this.cacheManager = cacheManager;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 普通获取缓存
     */
    public <T> T get(String cacheName, String key, Class<T> type) {
        Cache cache = cacheManager.getCache(cacheName);
        return cache != null ? cache.get(key, type) : null;
    }

    /**
     * 写入缓存
     */
    public void put(String cacheName, String key, Object value) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.put(key, value);
        }
    }

    /**
     * 删除缓存
     */
    public void evict(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.evict(key);
        }
    }

    /**
     * 带缓存穿透/击穿保护的获取方法
     * - 分布式锁防击穿
     * - 空值缓存防穿透
     */
    public <T> T getWithProtection(String cacheName, String key, Class<T> type, Supplier<T> loader) {
        // 1. 先查询缓存
        T value = get(cacheName, key, type);
        if (value != null) {
            return value;
        }

        // 2. 缓存未命中，加分布式锁
        String lockKey = "lock:" + cacheName + ":" + key;
        try {
            Boolean locked = redisTemplate.opsForValue()
                    .setIfAbsent(lockKey, "locked", 30, TimeUnit.SECONDS);

            if (Boolean.TRUE.equals(locked)) {
                // 3. 锁获取成功，查询数据源
                value = loader.get();
                if (value != null) {
                    // 4. 数据源查询成功，写入缓存
                    put(cacheName, key, value);
                } else {
                    // 5. 数据源查询为空，缓存空值（短TTL防穿透）
                    redisTemplate.opsForValue().set(
                            getRedisKey(cacheName, key),
                            new NullValue(),
                            30,
                            TimeUnit.SECONDS
                    );
                }
                return value;
            } else {
                // 6. 锁获取失败，重试
                Thread.sleep(100);
                return getWithProtection(cacheName, key, type, loader);
            }
        } catch (Exception e) {
            throw new RuntimeException("缓存获取失败", e);
        } finally {
            // 7. 释放锁
            redisTemplate.delete(lockKey);
        }
    }

    /**
     * 生成 Redis 缓存key
     */
    private String getRedisKey(String cacheName, String key) {
        return "multilevel:" + cacheName + ":" + key;
    }

    /**
     * 空值占位符
     */
    private static class NullValue {
    }
}
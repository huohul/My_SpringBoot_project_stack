package com.gxweb.multilevelcache.cache;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 修复所有报错：
 * 1. 移除 ValueRetrievalException（改用 RuntimeException）
 * 2. 修复 Redis 超时配置方法（适配 Spring Data Redis 2.7.x）
 * 3. 适配带密码的单机 Redis 配置
 */
@Primary
@Component
public class MultiLevelCacheManager implements CacheManager {

    private final Map<String, Cache> caches = new ConcurrentHashMap<>();
    // ========== 匹配你的 Redis 配置 ==========
    @Value("${spring.redis.host:localhost}")
    private String redisHost;
    @Value("${spring.redis.port:6379}")
    private int redisPort;
    @Value("${spring.redis.password:}")
    private String redisPassword;
    @Value("${spring.redis.timeout:3000ms}")
    private Duration redisTimeout;
    // 缓存配置
    @Value("${cache.redis.cache-prefix:multilevel:}")
    private String redisCachePrefix;
    @Value("${cache.redis.default-expire:1800s}")
    private Duration defaultRedisExpire;
    // 核心组件
    private CacheManager caffeineCacheManager;
    private RedisTemplate<String, Object> redisTemplate;

    // 初始化方法（修复所有报错点）
    @PostConstruct
    public void init() {
        // 1. 初始化 Caffeine 本地缓存
        CaffeineCacheManager localCacheManager = new CaffeineCacheManager();
        localCacheManager.setCaffeine(Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .expireAfterWrite(Duration.ofMinutes(10)));
        this.caffeineCacheManager = localCacheManager;

        // 2. 初始化 Redis 连接（修复超时配置 + 密码配置）
        // 2.1 配置 Redis 单机信息（含密码）
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName(redisHost);
        redisConfig.setPort(redisPort);
        if (!redisPassword.isEmpty()) {
            redisConfig.setPassword(redisPassword);
        }

        // 2.2 配置 Lettuce 客户端（修复超时设置方法）
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .commandTimeout(redisTimeout) // 正确设置超时时间（适配 Spring Data Redis 2.7.x）
                .build();

        // 2.3 创建单机连接工厂（禁用集群）
        LettuceConnectionFactory lettuceFactory = new LettuceConnectionFactory(redisConfig, clientConfig);
        lettuceFactory.afterPropertiesSet();

        // 3. 配置 RedisTemplate（FastJSON 序列化）
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceFactory);
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        GenericFastJsonRedisSerializer fastJsonSerializer = new GenericFastJsonRedisSerializer();
        // Key/HashKey 用 String 序列化
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        // Value/HashValue 用 FastJSON 序列化（支持 LocalDateTime）
        redisTemplate.setValueSerializer(fastJsonSerializer);
        redisTemplate.setHashValueSerializer(fastJsonSerializer);
        redisTemplate.afterPropertiesSet();
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Cache getCache(String name) {
        return caches.computeIfAbsent(name, MultiLevelCache::new);
    }

    @Override
    public Collection<String> getCacheNames() {
        return caches.keySet();
    }

    // 内部类（修复 ValueRetrievalException 报错）
    private class MultiLevelCache implements Cache {
        private final String name;
        private final Cache localCache;

        public MultiLevelCache(String name) {
            this.name = name;
            this.localCache = caffeineCacheManager.getCache(name);
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public Object getNativeCache() {
            return this;
        }

        // 基础 get 方法
        @Override
        public ValueWrapper get(Object key) {
            String cacheKey = buildCacheKey(key);
            // 1. 查本地缓存
            ValueWrapper localVal = localCache.get(key);
            if (localVal != null) {
                return localVal;
            }
            // 2. 查 Redis，命中回写本地
            Object redisVal = redisTemplate.opsForValue().get(buildRedisKey(cacheKey));
            if (redisVal != null) {
                localCache.put(key, redisVal);
                return new SimpleValueWrapper(redisVal);
            }
            return null;
        }

        // 泛型 get 方法
        @Override
        @SuppressWarnings("unchecked")
        public <T> T get(Object key, Class<T> type) {
            ValueWrapper wrapper = get(key);
            return wrapper != null ? (T) wrapper.get() : null;
        }

        // 带 Callable 的 get 方法（修复 ValueRetrievalException 报错）
        @Override
        public <T> T get(Object key, Callable<T> valueLoader) {
            // 先查缓存
            ValueWrapper wrapper = get(key);
            if (wrapper != null) {
                return (T) wrapper.get();
            }

            // 缓存未命中，加载数据（改用 RuntimeException 替代缺失的类）
            T value;
            try {
                value = valueLoader.call();
            } catch (Exception e) {
                throw new RuntimeException("加载缓存数据失败，key: " + key, e);
            }

            // 写入缓存并返回
            put(key, value);
            return value;
        }

        // 写缓存（双写本地+Redis）
        @Override
        public void put(Object key, Object value) {
            String cacheKey = buildCacheKey(key);
            // 1. 写本地缓存
            localCache.put(key, value);
            // 2. 写 Redis（加随机过期时间）
            long expire = defaultRedisExpire.getSeconds() + ThreadLocalRandom.current().nextInt(60);
            redisTemplate.opsForValue().set(buildRedisKey(cacheKey), value, expire, TimeUnit.SECONDS);
        }

        // 删除缓存（双删）
        @Override
        public void evict(Object key) {
            String cacheKey = buildCacheKey(key);
            localCache.evict(key);
            redisTemplate.delete(buildRedisKey(cacheKey));
        }

        // 清空缓存
        @Override
        public void clear() {
            localCache.clear();
            String redisPrefix = buildRedisKey(this.name + ":");
            redisTemplate.delete(redisTemplate.keys(redisPrefix + "*"));
        }

        // 可选方法（默认实现）
        @Override
        public ValueWrapper putIfAbsent(Object key, Object value) {
            ValueWrapper existing = get(key);
            if (existing == null) {
                put(key, value);
                return null;
            }
            return existing;
        }

        // 辅助方法：构建缓存键
        private String buildCacheKey(Object key) {
            return this.name + ":" + key.toString();
        }

        // 辅助方法：构建 Redis 完整键
        private String buildRedisKey(String cacheKey) {
            return redisCachePrefix + cacheKey;
        }
    }
}
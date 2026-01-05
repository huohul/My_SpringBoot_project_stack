package com.gxweb.multilevelcache.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration; // 单机配置
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory; // 单机连接工厂
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class MultiLevelCacheConfig {

    // 读取单机 Redis 配置
    @Value("${spring.redis.host:localhost}")
    private String redisHost;

    @Value("${spring.redis.port:6379}")
    private int redisPort;

    @Value("${spring.redis.password:}")
    private String redisPassword;

    // Caffeine 配置（保留不变）
    @Value("${cache.caffeine.initial-capacity}")
    private int initialCapacity;

    @Value("${cache.caffeine.maximum-size}")
    private long maximumSize;

    @Value("${cache.caffeine.expire-after-write}")
    private Duration expireAfterWrite;

    @Value("${cache.caffeine.expire-after-access}")
    private Duration expireAfterAccess;

    /**
     * 核心修改：手动创建单机 Redis 连接工厂（彻底禁用集群）
     */
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        // 1. 配置单机 Redis 信息
        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
        standaloneConfig.setHostName(redisHost);
        standaloneConfig.setPort(redisPort);
        if (!redisPassword.isEmpty()) {
            standaloneConfig.setPassword(redisPassword);
        }

        // 2. 创建单机连接工厂（非集群）
        LettuceConnectionFactory factory = new LettuceConnectionFactory(standaloneConfig);
        factory.afterPropertiesSet();
        return factory;
    }

    /**
     * RedisTemplate 配置（绑定单机连接工厂 + FastJSON 序列化）
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 绑定单机连接工厂（核心：不再使用 Spring 自动配置的集群工厂）
        template.setConnectionFactory(lettuceConnectionFactory());

        // FastJSON 序列化（保留你之前的配置）
        GenericFastJsonRedisSerializer fastJsonSerializer = new GenericFastJsonRedisSerializer();
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(fastJsonSerializer);
        template.setHashValueSerializer(fastJsonSerializer);

        template.afterPropertiesSet();
        return template;
    }

    /**
     * Caffeine 配置（保留不变）
     */
    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .initialCapacity(initialCapacity)
                .maximumSize(maximumSize)
                .expireAfterWrite(expireAfterWrite)
                .expireAfterAccess(expireAfterAccess)
                .recordStats();
    }
}
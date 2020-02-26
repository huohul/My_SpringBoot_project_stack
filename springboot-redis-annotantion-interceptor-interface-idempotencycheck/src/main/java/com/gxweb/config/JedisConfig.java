package com.gxweb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 配置见  https://blog.csdn.net/ljk126wy/article/details/104447656
 */
@Configuration
public class JedisConfig {

    @Value("${redis.host}")
    String host;
    @Value("${redis.port}")
    int port;
    @Value("${redis.timeout}")
    int timeout;

    @Bean
    @ConfigurationProperties("redis")
    public JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Bean(destroyMethod = "close")
    public JedisPool jedisPool() {
        JedisPool jedisPool = new JedisPool(jedisPoolConfig(), host, port, timeout*1000);
        return jedisPool;
    }

}

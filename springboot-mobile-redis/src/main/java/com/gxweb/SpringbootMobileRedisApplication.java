package com.gxweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ： CYQ
 * @date ：Created in 2021/1/20/020 21:03
 * @description ：启动类
 * @version: 1.0
 * SpringBoot实战：整合Redis、mybatis，封装RedisUtils工具类等 最后附Jmeter 压测工具
 * 来源 https://mp.weixin.qq.com/s/O2PubmG_q8gxxl8LKQ3fjg
 */
@MapperScan(basePackages = {"com.gxweb.mapper"}) //扫描DAO
@SpringBootApplication
public class SpringbootMobileRedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootMobileRedisApplication.class, args);
    }
}

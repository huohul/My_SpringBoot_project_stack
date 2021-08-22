package com.gxweb.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 整合 es 索引 为id 固定  测试添加 用postman row  json  格式添加
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.gxweb.system.dao"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}

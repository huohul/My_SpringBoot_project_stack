package com.gxweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/11 14:59
 * @description ：启动类
 * @version: 1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.gxweb.feign")
public class AppStuEs {
    public static void main(String[] args) {
        SpringApplication.run(AppStuEs.class, args);
    }
}

package com.gxweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ： CYQ
 * @date ：Created in 2019/07/14 16:33
 * @description ：启动类
 * @version: 1.0
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.gxweb.feign")  //扫描api 项目内 这个包 实体类包
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class);
        System.out.println("启动成功");
    }

}

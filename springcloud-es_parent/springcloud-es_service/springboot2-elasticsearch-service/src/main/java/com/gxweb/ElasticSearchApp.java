package com.gxweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author CYQ
 * @Title: App
 * @Description: 主程序入口
 * @Version:1.0.0
 * @date 2020年2月10日
 * SpringBoot整合ElasticSearch实现多版本的兼容
 * https://www.cnblogs.com/xuwujing/p/8998168.html
 * JestClient  在Test 测试中
 */
@EnableEurekaClient
@SpringBootApplication
public class ElasticSearchApp {
    public static void main(String[] args) {
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(ElasticSearchApp.class, args);
        System.out.println("ElasticSearch 程序正在运行...");
    }
}
 
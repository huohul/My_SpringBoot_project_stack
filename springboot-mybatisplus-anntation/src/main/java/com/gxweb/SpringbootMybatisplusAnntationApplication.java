package com.gxweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot+ mybatisplus  基于注解链表查询  整体架构 参考自：
 *                      G:\IDEAGIt\SpringBootAndSpringCloud_The_total_stack\Unable_to_import\my-java-demo\spring-boot-mybatisplus-demo
 *
 *     注解 改造 参照 本项目 内 springboot-jwt-security
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.gxweb.dao"}) //扫描DAO
public class SpringbootMybatisplusAnntationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisplusAnntationApplication.class, args);
    }

}

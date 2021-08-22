package com.gxweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 主要演示了 Spring Boot 如何集成 Mybatis 的多数据源。可以自己基于AOP实现多数据源，这里基于 Mybatis-Plus 提供的一个优雅的开源的解决方案来实现
 * G:\IDEAGIt\SpringBootAndSpringCloud_The_total_stack\spring-boot-demo\spring-boot-demo-multi-datasource-mybatis
 *
 *实体类主键id 已赋值雪花算法 无需set
 *
 * 主库 建议 只执行 INSERT UPDATE DELETE 操作  从库 建议 只执行 SELECT 操作
 *
 * 项目完成了主从复制与从主复制
 */
@SpringBootApplication
@MapperScan(basePackages = "com.gxweb.mapper")
public class SpringbootMultiDatasourceJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMultiDatasourceJpaApplication.class, args);
        System.out.println("启动成功");
    }
}
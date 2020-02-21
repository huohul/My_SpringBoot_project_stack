package com.gxweb;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Spring Boot 如何集成 JPA 的多数据源 来源：
 * G:\IDEAGIt\SpringBootAndSpringCloud_The_total_stack\spring-boot-demo\spring-boot-demo-multi-datasource-jpa
 *
 * 可直接拿来使用 修改  配置类 PrimaryJpaConfig /SecondJpaConfig  的持久层与实体类包名路径即可
 *              注意： 实体类 jpa  无法进行驼峰映射 就是 数据库user_id  这里 userId  无效 无法产生实体映射 所以 实体类与数据库列名保持一致即可
 */
@SpringBootApplication
public class SpringbootMultiDatasourceJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMultiDatasourceJpaApplication.class, args);
        System.out.println("启动成功");
    }


    //注入分布式id 雪花算法
    @Bean
    public Snowflake snowflake() {
        return IdUtil.createSnowflake(1, 1);
    }
}

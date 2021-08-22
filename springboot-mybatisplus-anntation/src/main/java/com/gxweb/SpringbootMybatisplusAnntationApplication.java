package com.gxweb;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * springboot+ mybatisplus  基于注解链表查询  加入了 index vue结合bootstrap 简单渲染数据 需引入 thymeleaf 依赖
 * <p>
 * 整体架构 参考自： 这个在2019 idea中
 * G:\IDEAGIt\SpringBootAndSpringCloud_The_total_stack\Unable_to_import\my-java-demo\spring-boot-mybatisplus-demo
 * <p>
 * 注解 改造 参照 本项目 内 springboot-jwt-security
 * <p>
 * -- 多条件查询 操作见  idea 2018 中springboot2.X   G:\IDEAGIt\SpringBootAndSpringCloud_The_total_stack\zwqh1992\Spring-Boot-2.X\spring-boot-mybatis-plus
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.gxweb.dao"}) //扫描DAO
public class SpringbootMybatisplusAnntationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisplusAnntationApplication.class, args);
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }

}

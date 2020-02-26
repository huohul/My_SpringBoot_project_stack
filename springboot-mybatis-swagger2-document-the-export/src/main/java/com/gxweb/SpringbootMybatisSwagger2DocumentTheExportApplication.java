package com.gxweb;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * spirngboot +  swagger2 + 文档导出
 *
 * 启动访问: http://localhost:8080/swagger-ui.html#/
 *
 * 参照来源：  G:\IDEAGIt\SpringBootAndSpringCloud_The_total_stack\zwqh1992\Spring-Boot-2.X\spring-boot-swagger2
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.gxweb.dao"}) //扫描DAO
public class SpringbootMybatisSwagger2DocumentTheExportApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisSwagger2DocumentTheExportApplication.class, args);
    }

    /**
     *   mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }

}

package com.gxweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.gxweb.core.dao"}) //扫描DAO
@SpringBootApplication
public class SpringbootJwtSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJwtSecurityApplication.class, args);
    }

}

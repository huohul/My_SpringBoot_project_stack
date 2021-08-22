package com.gxweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * springboot + jpa +thymeleaf 实现自定义查询条件 plus 版 多表 链表查询 + druid 配置与 监控页面
 * 监控页面 http://localhost:8080/druid/login.html
 * SpringBoot配置MySql数据库和Druid连接池 https://www.cnblogs.com/feiyangbahu/p/9842363.html
 */
@SpringBootApplication
public class SpringbootJpaMultilistApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaMultilistApplication.class, args);
    }

}

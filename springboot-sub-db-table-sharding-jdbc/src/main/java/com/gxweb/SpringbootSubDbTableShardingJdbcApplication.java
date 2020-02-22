package com.gxweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *  MybatisPlus集成 sharding-jdbc 实现分库分表操作
 */
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan("com.gxweb.mapper")
public class SpringbootSubDbTableShardingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSubDbTableShardingJdbcApplication.class, args);
    }

}

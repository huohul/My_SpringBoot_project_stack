package com.gxweb;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 实现分库分表 ms0 ms1  库 分表   读写分离 还未实现  读数据是从 sl0/1  表中读  数据为从主库中拉取  待以后完善
 * 来源 G:\IDEAGIt\SpringBootAndSpringCloud_The_total_stack\yudiandemingzi\spring-boot-sharding-sphere\sub-db-table-read-write
 */
@SpringBootApplication
public class SpringbootSubDbTableReadWriteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSubDbTableReadWriteApplication.class, args);
    }
    @Bean
    public Snowflake snowflake() {
        return IdUtil.createSnowflake(1, 1);
    }
}

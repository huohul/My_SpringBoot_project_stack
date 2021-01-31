package com.gxweb.springbootmultidatasourcemybatispluslog4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Springboot + mybatis plus +log4j2 进行分库分表 多数据源 主从复制 日志处理
 * 依据G:\IDEAGIt\My_SpringBoot_project_stack\springboot-multi-datasource-mybatisplus +
 * G:\IDEAGIt\SpringBootAndSpringCloud_The_total_stack\TiantianUpup\springboot-log-master\springboot-log4j2
 *   进行 改造 泛型 继承  运用优秀 减少代码冗余
 */
@SpringBootApplication
public class SpringbootMultiDatasourceMybatisplusLog4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMultiDatasourceMybatisplusLog4jApplication.class, args);
    }

}

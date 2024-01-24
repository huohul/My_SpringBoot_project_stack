package com.gxweb.iot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 增加了 DataSourceConfig 这个配置文件之后,需要添加druid连接池，单数据源自动装载时不会出这样的问题
 *
 * @Configuration 注解，表明这就是一个配置类,指示一个类声明一个或者多个@Bean 声明的方法并且由Spring容器统一管理，以便在运行时为这些bean生成bean的定义和服务请求的类。
 */
@Configuration
public class DataSourceConfig {

    /**
     * 注入主库数据源
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return new DruidDataSource();
    }

    /**
     * 注入从库数据源
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return new DruidDataSource();
    }


    /**
     * 配置选择数据源
     *
     * @param masterDataSource
     * @param slaveDataSource
     * @return DataSource
     */
    @Bean
    public DataSource myRoutingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource, @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DBTypeEnum.MASTER, masterDataSource);
        targetDataSource.put(DBTypeEnum.SLAVE, slaveDataSource);

        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
        //找不到用默认数据源
        myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
        //可选择目标数据源
        myRoutingDataSource.setTargetDataSources(targetDataSource);

        return myRoutingDataSource;

    }
}
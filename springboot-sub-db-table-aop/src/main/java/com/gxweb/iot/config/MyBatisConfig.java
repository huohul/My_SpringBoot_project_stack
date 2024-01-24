package com.gxweb.iot.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 配置Mybatis指定数据源:SqlSessionFactory和事务管理器
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig {

    /**
     * 注入自己重写的数据源
     */
    @Resource(name = "myRoutingDataSource")
    private DataSource myRoutingDataSource;

    /**
     * 配置SqlSessionFactory
     *
     * @return SqlSessionFactory
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(myRoutingDataSource);

        //ResourcePatternResolver(资源查找器)定义了getResources来查找资源
        //PathMatchingResourcePatternResolver提供了以classpath开头的通配符方式查询,否则会调用ResourceLoader的getResource方法来查找
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocation));

        return sqlSessionFactoryBean.getObject();
    }


    /**
     * 事务管理器，不写则事务不生效：事务需要知道当前使用的是哪个数据源才能进行事务处理
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(myRoutingDataSource);
    }

//    /**
//     * 当自定义数据源，用户必须覆盖SqlSessionTemplate,开启BATCH处理模式
//     *
//     * @param sqlSessionFactory
//     * @return
//     */
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
//    }

}
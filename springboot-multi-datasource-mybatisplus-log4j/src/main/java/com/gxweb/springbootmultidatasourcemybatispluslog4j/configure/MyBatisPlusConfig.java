package com.gxweb.springbootmultidatasourcemybatispluslog4j.configure;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis plus配置类
 *
 * @author cyq
 * @version 1.0
 * @Date 2019/08/02 16:11
 */
@Configuration
public class MyBatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

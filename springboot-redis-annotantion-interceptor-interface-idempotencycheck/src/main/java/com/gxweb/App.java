package com.gxweb;

import com.gxweb.interceptor.ApiIdempotentInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Spring Boot + Redis + 注解 + 拦截器来实现接口幂等性校验  来源链接： https://mp.weixin.qq.com/s/GWRKJzM_t1yqvZaSoptdmw
 * 概念： 幂等性, 通俗的说就是一个接口, 多次发起同一个请求, 必须保证操作只能执行一次
 * 业务逻辑：
 *      为需要保证幂等性的每一次请求创建一个唯一标识token, 先获取token, 并将此token存入redis, 请求接口时, 将此token放到header或者作为请求参数请求接口,
 *      后端接口判断redis中是否存在此token:
 *      如果存在, 正常处理业务逻辑, 并从redis中删除此token, 那么, 如果是重复请求, 由于token已被删除, 则不能通过校验, 返回请勿重复操作提示
 *      如果不存在, 说明参数不合法或者是重复请求, 返回提示即可
 *
 *      但是业务 携带token   一次正常访问都不行 ？？？？  卧槽
 */

@SpringBootApplication
public class App extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    /**
     * 跨域
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 接口幂等性拦截器
        registry.addInterceptor(apiIdempotentInterceptor());
        super.addInterceptors(registry);
    }

    @Bean
    public ApiIdempotentInterceptor apiIdempotentInterceptor() {
        return new ApiIdempotentInterceptor();
    }


}

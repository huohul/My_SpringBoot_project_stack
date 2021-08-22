package com.xkcoding.orm.mybatis;

import com.xkcoding.orm.mybatis.service.WeatherServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.ws.Endpoint;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-08 10:52
 */
@MapperScan(basePackages = {"com.xkcoding.orm.mybatis.mapper"})
@SpringBootApplication
public class SpringBootDemoOrmMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoOrmMybatisApplication.class, args);
        String url = "http://localhost:80/weather";
        Endpoint.publish(url, new WeatherServiceImpl());
    }

}

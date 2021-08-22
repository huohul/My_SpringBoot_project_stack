package com.xkcoding.orm.mybatis.service;


import com.xkcoding.orm.mybatis.SpringBootDemoOrmMybatisApplicationTests;
import com.xkcoding.orm.mybatis.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WeatherServiceImplTest extends SpringBootDemoOrmMybatisApplicationTests {
    @Autowired
    private WeatherService weatherService;

    @Test
    public void queryWeatherByCityName() {
    }

    @Test
    public void findAllWebServiceInface() {
        List<User> allWebServiceInface = weatherService.findAllWebServiceInface();
    }
}

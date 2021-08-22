package com.xkcoding.orm.mybatis.service;

import com.xkcoding.orm.mybatis.entity.User;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface WeatherService {

    public String queryWeatherByCityName(String cityname);

    List<User> findAllWebServiceInface();

}

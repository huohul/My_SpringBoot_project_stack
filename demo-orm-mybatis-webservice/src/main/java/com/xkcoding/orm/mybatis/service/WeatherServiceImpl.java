package com.xkcoding.orm.mybatis.service;

import com.xkcoding.orm.mybatis.entity.User;
import com.xkcoding.orm.mybatis.mapper.UserMapper;
import com.xkcoding.orm.mybatis.util.SpringContextUtils;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;

@WebService
@Service
public class WeatherServiceImpl implements WeatherService {
    private UserMapper userMapper;

    @Override
    public String queryWeatherByCityName(String cityname) {
        System.out.println(cityname + "进行天气请求!");
        if ("北京".equals(cityname)) {
            return cityname + "雾霾!!!";
        }
        return cityname + "天气不错!!!";
    }

    @Override
    public List<User> findAllWebServiceInface() {
        userMapper = (UserMapper) SpringContextUtils.getBean(UserMapper.class);
        return userMapper.selectAllUser();
    }
}


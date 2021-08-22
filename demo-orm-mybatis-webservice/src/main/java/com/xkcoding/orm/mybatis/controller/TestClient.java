package com.xkcoding.orm.mybatis.controller;


import com.xkcoding.orm.mybatis.wsdl.WeatherServiceImpl;
import com.xkcoding.orm.mybatis.wsdl.WeatherServiceImplService;

public class TestClient {

    public static void main(String[] args) {

        /*第一种调用方式，基于服务视图名称对象的调用方式，缺点是如果服务端的wsdl地址更换，需要再重新反编译代码运行*/
        WeatherServiceImplService weatherService = new WeatherServiceImplService();//创建服务视图对象
        WeatherServiceImpl weatherServiceImpl = weatherService.getWeatherServiceImplPort();
        String cityName = weatherServiceImpl.queryWeatherByCityName("北京");
        System.out.println(cityName);

    }


}

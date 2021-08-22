package com.xkcoding.orm.mybatis.controller;

import com.xkcoding.orm.mybatis.wsdl.User;
import com.xkcoding.orm.mybatis.wsdl.WeatherServiceImpl;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/*标准的WSDL调用方式，基于JDK，规避了服务端wsdl地址更换需要重新反编译代码的问题，只需要修改wsdl地址即可继续使用*/
public class TestClient1 {

    public static void main(String[] args) {

        String wsdlUrl = "http://localhost:80/weather?wsdl";

        /*参数1：WSDL的目标命名空间 > targetNamespace ，参数2：WSDL的服务视图名 > service的name*/
        QName qName = new QName("http://service.mybatis.orm.xkcoding.com/", "WeatherServiceImplService");
        //发布服务 接口报名反转
        try {
            Service service = Service.create(new URL(wsdlUrl), qName);//参数1：WSDL的地址,参数2：WSDL命名空间对象
            WeatherServiceImpl method = service.getPort(WeatherServiceImpl.class);//通用服务对象获取服务核心对象(portType的name)
            String result = method.queryWeatherByCityName("天津");
            List<User> allWebServiceInface = method.findAllWebServiceInface();
            System.out.println(result);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }


}

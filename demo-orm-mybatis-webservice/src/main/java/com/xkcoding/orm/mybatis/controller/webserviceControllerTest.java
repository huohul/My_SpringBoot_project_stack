package com.xkcoding.orm.mybatis.controller;

import com.xkcoding.orm.mybatis.mapper.UserMapper;
import com.xkcoding.orm.mybatis.wsdl.User;
import com.xkcoding.orm.mybatis.wsdl.WeatherServiceImpl;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2021/8/22 0:10
 * @description ：
 * @version: 1.0
 */
@RestController
@RequestMapping("/public/common/")
public class webserviceControllerTest {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/getAll")
    public Object getAll() {
        String wsdlUrl = "http://localhost:80/weather?wsdl";

        /*参数1：WSDL的目标命名空间 > targetNamespace ，参数2：WSDL的服务视图名 > service的name*/
        QName qName = new QName("http://service.mybatis.orm.xkcoding.com/", "WeatherServiceImplService");
        //发布服务 接口报名反转
        List<User> allWebServiceInface = null;
        try {
            Service service = Service.create(new URL(wsdlUrl), qName);//参数1：WSDL的地址,参数2：WSDL命名空间对象
            WeatherServiceImpl method = service.getPort(WeatherServiceImpl.class);//通用服务对象获取服务核心对象(portType的name)
            allWebServiceInface = method.findAllWebServiceInface();
            System.out.println(allWebServiceInface.size());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return allWebServiceInface;

    }

    //http://localhost:8080/public/common/foo/scan/1
    @RequestMapping("/foo/scan/{limit}")
    @Transactional
    public void scanFoo(@PathVariable("limit") Integer limit) {
        List<com.xkcoding.orm.mybatis.entity.User> list = new ArrayList<>();
        try (Cursor<com.xkcoding.orm.mybatis.entity.User> cursor = userMapper.scan(limit)) {
            cursor.forEach(foo -> {
                System.out.println("foo:" + foo + "\n");
                System.out.println("result:" + getAll() + "\n");
                list.add(foo);
            });
            System.out.println(list.size() + "---" + list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

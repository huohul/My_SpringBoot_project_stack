package com.gxweb.iot.controller;

import com.gxweb.iot.entity.UserEntity;
import com.gxweb.iot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;


/**
 * @author xub
 * @Description: 接口测试
 * @date 2019/8/24 下午6:31
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/listUser")
    public List<UserEntity> listUser() {
        return userService.findAll();
    }

    @RequestMapping("/insertUser")
    public void insertUser() {
        UserEntity userEntity = new UserEntity();
        Random random = new Random();
        userEntity.setId((long) random.nextInt());
        userEntity.setName(UUID.randomUUID().toString().replaceAll("-", ""));
        userEntity.setSex("?");
        userEntity.setAge(0);
        userEntity.setCreateTime(new Date());
        userEntity.setUpdateTime(new Date());
        userEntity.setStatus(0);
        userService.insertUser(userEntity);
    }

}

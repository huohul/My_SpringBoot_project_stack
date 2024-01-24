package com.gxweb.iot.service;

import com.gxweb.iot.entity.UserEntity;
import com.gxweb.iot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    //@Master
    public List<UserEntity> findAll() {
        return userMapper.selectAll();
    }

    public void insertUser(UserEntity user) {
        userMapper.insertForeach(user);
    }

//    void update(UserEntity user);
//
//    void delete(Long id);

}
package com.gxweb.service.impl;

import com.gxweb.entity.User;
import com.gxweb.service.UserService;

import java.util.ArrayList;

/**
 * @author ： CYQ
 * @date ：Created in 2021/2/11/011 21:16
 * @description ：
 * @version: 1.0
 */
public class UserServiceImpl implements UserService {
    private ArrayList<User> user = new ArrayList<User>();

    private static UserServiceImpl userserviceimpl = new UserServiceImpl(); //单例

    private UserServiceImpl() {

    }

    public static UserServiceImpl getUserserviceimpl() {
        return userserviceimpl;
    }

    @Override
    public User login(User a) { //把车牌录入
        user.add(a);
        return a;
    }
}

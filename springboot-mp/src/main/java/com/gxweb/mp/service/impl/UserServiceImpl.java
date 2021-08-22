package com.gxweb.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxweb.mp.mappers.UserMapper;
import com.gxweb.mp.po.User;
import com.gxweb.mp.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author ： CYQ
 * @date ：Created in 2021/6/6 21:23
 * @description ：
 * @version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

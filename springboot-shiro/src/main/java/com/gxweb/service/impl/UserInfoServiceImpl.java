package com.gxweb.service.impl;

import com.gxweb.dao.UserInfoDao;
import com.gxweb.entity.UserInfo;
import com.gxweb.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/29 19:42
 * @description ：
 * @version: 1.0
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUsername(String username) {
        return userInfoDao.findByUsername(username);
    }
}

package com.gxweb.service;

import com.gxweb.entity.Admin;

/**
 * @author ： CYQ
 * @date ：Created in 2021/2/11/011 21:08
 * @description ：管理员业务接口
 * @version: 1.0
 */
public interface AdminService {
    //登录
    Admin login(String username, String password);

    //注册
    boolean register(Admin admin);
}

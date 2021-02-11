package com.gxweb.service.impl;

import com.gxweb.entity.Admin;
import com.gxweb.service.AdminService;

import java.util.ArrayList;

/**
 * @author ： CYQ
 * @date ：Created in 2021/2/11/011 21:09
 * @description ：管理员类实现类
 * @version: 1.0
 */
public class AdminServiceImpl implements AdminService {

    private ArrayList<Admin> adminList = new ArrayList<Admin>();
    //单例饿汉模式
    private static final AdminServiceImpl adminSeriver = new AdminServiceImpl();

    public static AdminServiceImpl getAdminSeriver() {
        return adminSeriver;
    }

    private AdminServiceImpl() {
    }

    @Override //管理员登录
    public Admin login(String username, String password) {  //登录
        for (Admin admin : adminList) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }

    @Override //添加管理员
    public boolean register(Admin admin) {
        boolean flag = adminList.add(admin);//添加管理员
        return flag;
    }

}

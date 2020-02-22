package com.gxweb.controller;


import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import com.gxweb.entity.TabUser0;
import com.gxweb.service.TabUser0Service;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;


/**
 * @Description: 接口测试
 *
 * @author xub
 * @date 2019/8/24 下午6:31
 */
@RestController
public class UserController {

    /**
     * 服务对象
     */
    @Resource
    private TabUser0Service tabUser0Service;


    @Autowired
    private Snowflake snowflake;

    /**
     * 模拟插入数据
     */
    List<TabUser0> userList = Lists.newArrayList();
    /**
     * 初始化插入数据
     */
    @PostConstruct
    private void getData() {
        for (int i = 10; i > 0; i--) {
            for (int j = 20; j > 0; j--) {
                userList.add(new TabUser0(snowflake.nextId(),"小小", "女", RandomUtil.randomInt(100)));
                userList.add(new TabUser0(snowflake.nextId(),"爸爸", "男", RandomUtil.randomInt(100)));
                userList.add(new TabUser0(snowflake.nextId(),"妈妈", "女", RandomUtil.randomInt(100)));
                userList.add(new TabUser0(snowflake.nextId(),"爷爷", "男", RandomUtil.randomInt(100)));
                userList.add(new TabUser0(snowflake.nextId(),"奶奶", "女", RandomUtil.randomInt(100)));
                userList.add(new TabUser0(snowflake.nextId(),RandomUtil.randomString(10), "女", RandomUtil.randomInt(100)));
            }
        }
    }
    /**
     * @Description: 批量保存用户
     */
    @PostMapping("save-user")
    public Object saveUser() {
        return tabUser0Service.insertForeach(userList);
    }


    /**
     * @Description: 获取用户列表
     */
    @GetMapping("list-user")
    public Object listUser(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        List<TabUser0> tabUser0s = tabUser0Service.queryAllByLimit();
        return tabUser0s;
    }


}

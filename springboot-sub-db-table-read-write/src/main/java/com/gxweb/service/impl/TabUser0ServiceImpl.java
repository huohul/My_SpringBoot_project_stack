package com.gxweb.service.impl;

import com.gxweb.dao.TabUser0Dao;
import com.gxweb.entity.TabUser0;
import com.gxweb.service.TabUser0Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (TabUser0)表服务实现类
 *
 * @author CYQ
 * @since 2020-02-22 23:36:27
 */
@Service("tabUser0Service")
public class TabUser0ServiceImpl implements TabUser0Service {

    @Resource
    private TabUser0Dao tabUser0Dao;


    @Override
    public List<TabUser0> queryAllByLimit() {
        return tabUser0Dao.selectAll();
    }


    @Override
    public String insertForeach(List<TabUser0> userVOList) {
        for (TabUser0 user : userVOList) {
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setStatus(0);
        }
        //批量插入数据
        tabUser0Dao.insertForeach(userVOList);
        return "保存成功";
    }

}
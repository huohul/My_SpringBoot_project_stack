package com.gxweb.service;

import com.gxweb.entity.TabUser0;

import java.util.List;

/**
 * (TabUser0)表服务接口
 *
 * @author CYQ
 * @since 2020-02-22 23:36:27
 */
public interface TabUser0Service {


    List<TabUser0> queryAllByLimit();


    /**
     *  批量 保存用户信息
     * @param userVOList
     */
    String  insertForeach(List<TabUser0> userVOList);

}
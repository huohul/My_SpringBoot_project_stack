package com.gxweb.dao;

import com.gxweb.entity.TabUser0;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TabUser0)表数据库访问层
 *
 * @author CYQ
 * @since 2020-02-22 23:36:27
 */
@Mapper
public interface TabUser0Dao {

    /**
     * 批量插入
     *
     * @param list 插入集合
     * @return 插入数量
     */
    int insertForeach(List<TabUser0> list);


    List<TabUser0> selectAll();
}
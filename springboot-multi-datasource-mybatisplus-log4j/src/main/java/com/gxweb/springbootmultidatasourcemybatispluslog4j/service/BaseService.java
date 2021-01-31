package com.gxweb.springbootmultidatasourcemybatispluslog4j.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2021/1/30/030 13:04
 * @description ：基本业务类接口 公共实用
 * @version: 1.0
 */

public interface BaseService<T> extends IService<T> {
    /**
     * 添加
     *
     * @param obj
     * @return
     */
    boolean insert(T obj);

    /**
     * 批量添加
     *
     * @param objList
     * @return
     */
    boolean insertBatch(List<T> objList);

    /**
     * 根据id修改
     */
    boolean modifyById(T obj);

    /**
     * 根据传入参数条件进行删除
     *
     * @param obj
     * @return
     */
    boolean delete(T obj);

    /**
     * 根据id批量删除
     *
     * @param idList
     * @return
     */
    boolean deleteByIds(List<Long> idList);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    boolean deleteById(Long id);

    /**
     * 根据id查找
     */
    T selectById(Long id);

    /**
     * 根据条件进行查询
     *
     * @param obj
     * @return
     */
    List<T> selectList(T obj);

    /**
     * 根据id批量查询
     *
     * @param idList
     * @return
     */
    Collection<T> selectByIds(List<Long> idList);

    /**
     * 主库查询所有
     * @return
     */
    List<T> findMasAll();
    /**
     * 分页查询
     *
     * @param pageNo   页码
     * @param pageSize 页数
     * @param obj
     * @return
     */
    IPage<T> selectPage(T obj, Integer pageNo, Integer pageSize);

}



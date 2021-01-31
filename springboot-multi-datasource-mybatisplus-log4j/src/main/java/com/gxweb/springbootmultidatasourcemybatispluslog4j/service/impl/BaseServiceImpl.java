package com.gxweb.springbootmultidatasourcemybatispluslog4j.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxweb.springbootmultidatasourcemybatispluslog4j.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2021/1/30/030 13:06
 * @description ：
 * @version: 1.0
 */

public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {
    Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);

    /**
     * 类上 {@code @DS("slave")} 代表默认从库，在方法上写 {@code @DS("master")} 代表默认主库
     *
     * @param obj 用户
     */
    @DS("master")
    @Override
    public boolean insert(T obj) {
        return this.save(obj);
    }

    /**
     * 批量添加
     *
     * @param objList
     * @return
     */
    @DS("master")
    @Override
    public boolean insertBatch(List<T> objList) {
        return this.saveBatch(objList);
    }


    /**
     * 根据id修改
     *
     * @param obj
     */
    @DS("master")
    @Override
    public boolean modifyById(T obj) {
        return this.updateById(obj);
    }

    /**
     * 根据传入参数条件进行删除
     *
     * @param obj
     * @return
     */
    @DS("master")
    @Override
    public boolean delete(T obj) {
        Wrapper wrapper = new QueryWrapper<T>(obj);
        return this.remove(wrapper);
    }

    /**
     * 根据id批量删除
     *
     * @param idList
     * @return
     */
    @DS("master")
    @Override
    public boolean deleteByIds(List<Long> idList) {
        return this.removeByIds(idList);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @DS("master")
    @Override
    public boolean deleteById(Long id) {
        return this.removeById(id);
    }

    /**
     * 根据id查找
     *
     * @param id
     */
    @DS("master")
    @Override
    public T selectById(Long id) {
        return this.getById(id);
    }

    /**
     * 根据条件进行查询
     *
     * @param obj
     * @return
     */
    @DS("master")
    @Override
    public List<T> selectList(T obj) {
        Wrapper wrapper = new QueryWrapper<T>(obj);
        return this.list(wrapper);
    }

    /**
     * 根据id批量查询
     *
     * @param idList
     * @return
     */
    @DS("master")
    @Override
    public Collection<T> selectByIds(List<Long> idList) {
        return this.listByIds(idList);
    }

    /**
     * 主库查询所有
     *
     * @return
     */
    @DS("master")
    @Override
    public List<T> findMasAll() {
        return baseMapper.selectList(new QueryWrapper<>());
    }

    /**
     * 分页查询
     *
     * @param pageNo   页码
     * @param pageSize 页数
     * @param obj
     * @return
     */
    @DS("master")
    @Override
    public IPage<T> selectPage(T obj, Integer pageNo, Integer pageSize) {
        if (obj == null || pageNo == null || pageSize == null) {
            log.error("传参错误");
        }

        log.info("根据查询条件：{} 查询，当前页码：{}，分页数：{}", obj, pageNo, pageSize);
        Page<T> page = new Page<T>(pageNo, pageSize);
        Wrapper wrapper = new QueryWrapper<T>(obj);
        return this.page(page, wrapper);
    }

}

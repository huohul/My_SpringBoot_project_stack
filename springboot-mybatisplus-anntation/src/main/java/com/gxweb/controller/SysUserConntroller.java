package com.gxweb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxweb.dao.SysUserDao;
import com.gxweb.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/25 18:32
 * @description ：多条件查询
 * @version: 1.0
 */
@RestController
@RequestMapping("/user")
public class SysUserConntroller {

    @Autowired
    private SysUserDao userDao;

    /**
     * 新增用户  http://127.0.0.1:8080/user/insert
     *
     * @return
     */
    @RequestMapping(value = "insert")
    public String insert(SysUserEntity user) {
        if (user == null)
            return "参数错误";
        userDao.insert(user);
        return "insert success";
    }

    /**
     * 根据id修改用户  http://127.0.0.1:8080/user/updateById
     *
     * @return
     */
    @RequestMapping(value = "updateById")
    public String updateById(SysUserEntity user) {
        if (user == null)
            return "参数错误";
        userDao.updateById(user);
        return "update success";
    }

    /**
     * 根据entity条件修改信息  http://127.0.0.1:8080/user/update
     *
     * @return
     */
    @RequestMapping(value = "update")
    public String update(SysUserEntity user) {
        if (user == null)
            return "参数错误";

        userDao.update(user, new QueryWrapper<SysUserEntity>().eq("username", user.getUsername()));
        return "update success";
    }

    /**
     * 根据id查询用户   http://127.0.0.1:8080/user/selectById?id=1
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "selectById")
    public SysUserEntity selectById(Long id) {
        return userDao.selectById(id);
    }

    /**
     * 根据entity条件查询总记录数   http://127.0.0.1:8080/user/selectCount?status=NORMAL
     *
     * @param status 状态正常的 NORMAL
     * @return
     */
    @RequestMapping(value = "selectCount")
    public int selectCount(String status) {
        return userDao.selectCount(new QueryWrapper<SysUserEntity>().eq("status", status));
    }

    /**
     * 根据 entity 条件，查询一条记录，返回的是实体，如果表内有两条或以上的相同数据则会报错，
     * 可以用来判断某类数据是否已存在  http://127.0.0.1:8080/user/selectOne?username=朝雾轻寒&sex=男
     *
     * @param
     * @return
     */
    @RequestMapping(value = "selectOne")
    public SysUserEntity selectOne(SysUserEntity userEntity) {
        System.out.println(userEntity);
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(userEntity);
        return userDao.selectOne(queryWrapper);
    }

    /**
     * 根据entity条件查询返回第一个字段的值（返回id列表）  http://127.0.0.1:8080/user/selectObjs?status=NORMAL
     *
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "selectObjs")
    public List<Object> selectObjs(SysUserEntity userEntity) {
        System.out.println(userEntity);
        return userDao.selectObjs(new QueryWrapper<SysUserEntity>().setEntity(userEntity));
    }

    /**
     * 根据map条件查询返回多条数据   http://127.0.0.1:8080/user/selectByMap?username=admin&status=NORMAL
     *
     * @return   [{},{}]  返回有序
     */
    @RequestMapping(value = "selectByMap")
    public List<SysUserEntity> selectByMap( Map<String, Object> map) {
//        Map<String, Object> map=new HashMap<String, Object>();
//        map.put("user_name", username);
//        map.put("user_sex",sex);
        return userDao.selectByMap(map);
    }

    /**
     * 根据entity条件查询返回多条数据 http://127.0.0.1:8080/user/selectList?status=NORMAL
     *
     * @param status
     * @return
     */
    @RequestMapping(value = "selectList")
    public List<SysUserEntity> selectList(String status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);
        return userDao.selectList(new QueryWrapper<SysUserEntity>().allEq(map));
    }

    /**
     * 根据entity条件查询返回多条数据 http://127.0.0.1:8080/user/selectMaps?status=NORMAL
     *
     * @param status
     * @return List<Map < String, Object>>  同上上 只是返回值不一样   [{},{}]  返回无序
     */
    @RequestMapping(value = "selectMaps")
    public List<Map<String, Object>> selectMaps(String status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);
        return userDao.selectMaps(new QueryWrapper<SysUserEntity>().allEq(map));
    }


    /**
     * 根据ID批量查询,主键ID列表(不能为 null 以及 empty) http://127.0.0.1:8080/user/selectBatchIds
     *
     * @return
     */
    @RequestMapping(value = "selectBatchIds")
    public List<SysUserEntity> selectBatchIds() {
        List<Long> ids=new ArrayList<Long>();
        ids.add(1L);
        ids.add(2L);
        return userDao.selectBatchIds(ids);
    }

    /**
     * 分页查询，需先配置分页插件bean，否则分页无效（如有pagehelper需先去除）   http://127.0.0.1:8080/user/selectPage
     *
     * @return
     */
    @RequestMapping(value = "selectPage")
    public Page<SysUserEntity> selectPage() {
        Page<SysUserEntity> page= (Page<SysUserEntity>) userDao.selectPage(new Page<>(1,5), new QueryWrapper<SysUserEntity>().eq("username", "admin"));
        System.out.println("records:"+page.getRecords());
        return page;
    }

    /**
     * 分页查询，需先配置分页插件bean，否则分页无效（如有pagehelper需先去除）   http://127.0.0.1:8080/user/selectPage
     *
     * @return 返回值不一样  无序
     */
    @RequestMapping(value = "selectMapsPage")
    public Page<Map<String, Object>> selectMapsPage() {
        Page<Map<String, Object>> page= (Page<Map<String, Object>>) userDao.selectMapsPage(new Page<>(1,5), new QueryWrapper<SysUserEntity>().eq("username", "admin"));
        System.out.println("records:"+page.getRecords());
        return page;
    }

    /**
     * 根据id删除用户  http://127.0.0.1:8080/user/deleteById?id=24
     *
     * @return
     */
    @RequestMapping(value = "deleteById")
    public String deleteById(Long id) {
        userDao.deleteById(id);
        return "delete success";
    }

    /**
     * 根据entity条件删除用户  http://127.0.0.1:8080/user/delete?id=24
     *
     * @return
     */
    @RequestMapping(value = "delete")
    public String delete(Long id) {
        userDao.delete(new QueryWrapper<SysUserEntity>().eq("user_id", id));
        return "delete success";
    }

    /**
     * 根据map条件删除用户  http://127.0.0.1:8080/user/deleteByMap?username=xx&sex=x
     *
     * @return
     */
    @RequestMapping(value = "deleteByMap")
    public String deleteByMap(String username, String sex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_name", username);
        map.put("user_sex", sex);
        userDao.deleteByMap(map);
        return "delete success";
    }

    /**
     * 根据ID批量删除,主键ID列表(不能为 null 以及 empty) http://127.0.0.1:8080/user/deleteBatchIds
     *
     * @return
     */
    @RequestMapping(value = "deleteBatchIds")
    public String deleteBatchIds() {
        List<Long> ids = new ArrayList<Long>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        userDao.deleteBatchIds(ids);
        return "delete success";
    }

}

package com.gxweb.system.service;

import com.gxweb.pojo.Student;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface StudentService {

    /***
     * 查询所有品牌
     * @return
     */
    List<Student> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Student findById(Integer id);

    /***
     * 新增品牌
     * @param student
     */
    void add(Student student);

    /***
     * 修改品牌数据
     * @param student
     */
    void update(Student student);

    /***
     * 删除品牌
     * @param id
     */
    void delete(Integer id);

    /***
     * 多条件搜索品牌方法
     * @param searchMap
     * @return
     */
    List<Student> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Student> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    Page<Student> findPage(Map<String, Object> searchMap, int page, int size);




}

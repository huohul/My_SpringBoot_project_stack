package com.gxweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxweb.entity.Person;

import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/21 22:41
 * @description ：业务层
 * @version: 1.0
 */
public interface PersonService extends IService<Person> {

    // 下方法都是主库接口  从库不可调用

    /**
     * 添加 Person
     *
     * @param person 用户
     */
    void addMasUser(Person person);


    /**
     * 主库根据查询
     * @param id
     * @return
     */
    Person fidByMasId(Long id);

    /**
     * 主库查询所
     * @return
     */
    List<Person> findMasAll();

//
//    /**
//     * 分页条件查询
//     */
//    public List<Person> searchMas(Map map, Pageable pageable);
}

package com.gxweb.service;

import com.gxweb.entity.Person;

import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/16 17:02
 * @description ：
 * @version: 1.0
 */

public interface PersonService {
    /**
     * 新增用户
     *
     * @param person
     * @return
     */
    boolean addPerson(Person person);

    /**
     * 修改用户
     *
     * @param person
     * @return
     */
    boolean updatePerson(Person person);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    boolean deletePerson(Long id);

    /**
     * 根据用户名字查询用户信息
     *
     * @param id
     */
    Person findPersonById(Long id);

    /**
     * 查询所有
     *
     * @return
     */
    List<Person> findAll();

}

package com.gxweb.service;

import com.gxweb.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    boolean updateUser(User user);


    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    boolean deleteUser(Long id);

    /**
     * 根据用户名字查询用户信息
     *
     * @param id
     */
    User findUserById(Long id);

    /**
     * 查询所有
     *
     * @return
     */
    List<User> findAll();

}

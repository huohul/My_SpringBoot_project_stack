package com.gxweb.service.impl;

import com.gxweb.dao.UserDao;
import com.gxweb.entity.User;
import com.gxweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/07 14:18
 * @description ：
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public boolean addUser(User user) {
        boolean flag = false;
        try {
            userDao.save(user);
            flag = true;
        } catch (Exception e) {
            System.out.println("新增失败!");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateUser(User user) {
        boolean flag = false;
        try {
            userDao.save(user);
            flag = true;
        } catch (Exception e) {
            System.out.println("修改失败!");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteUser(Long id) {
        boolean flag = false;
        try {
            userDao.deleteById(id);
            flag = true;
        } catch (Exception e) {
            System.out.println("删除失败!");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public User findUserById(Long id) {
        return userDao.findById(id).get();
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();

    }

    // 普通分页
    @Override
    @Transactional(readOnly = true)  // 只读事务
    public Page<User> getPage(Integer pageNum, Integer pageLimit) {
        Pageable pageable = new PageRequest(pageNum - 1, pageLimit);
        return userDao.findAll(pageable);
    }

    // 分页排序
    @Override
    @Transactional(readOnly = true)
    public Page<User> getPageSort(Integer pageNum, Integer pageLimit) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = new PageRequest(pageNum - 1, pageLimit, sort);
        return userDao.findAll(pageable);
    }
}

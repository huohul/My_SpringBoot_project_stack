package com.gxweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxweb.dao.UserDao;
import com.gxweb.entity.SwaggerUserEntity;
import com.gxweb.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/26 13:41
 * @description ：
 * @version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, SwaggerUserEntity> implements UserService {


}

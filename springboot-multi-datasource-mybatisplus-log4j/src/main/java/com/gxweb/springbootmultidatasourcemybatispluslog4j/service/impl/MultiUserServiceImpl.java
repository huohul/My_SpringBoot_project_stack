package com.gxweb.springbootmultidatasourcemybatispluslog4j.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.gxweb.springbootmultidatasourcemybatispluslog4j.mapper.MultiUserMapper;
import com.gxweb.springbootmultidatasourcemybatispluslog4j.po.MultiUser;
import com.gxweb.springbootmultidatasourcemybatispluslog4j.service.MultiUserService;
import org.springframework.stereotype.Service;

/**
 * @author ： CYQ
 * @date ：Created in 2021/1/30/030 13:15
 * @description ：
 * @version: 1.0
 */
@DS("slave")
@Service
public class MultiUserServiceImpl extends BaseServiceImpl<MultiUserMapper, MultiUser> implements MultiUserService {
}

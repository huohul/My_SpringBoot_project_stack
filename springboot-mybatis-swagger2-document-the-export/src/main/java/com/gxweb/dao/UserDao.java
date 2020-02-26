package com.gxweb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxweb.entity.SwaggerUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/26 13:42
 * @description ：持久层
 * @version: 1.0
 */
@Mapper
public interface UserDao extends BaseMapper<SwaggerUserEntity> {
}

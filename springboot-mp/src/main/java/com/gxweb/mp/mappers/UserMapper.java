package com.gxweb.mp.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxweb.mp.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2021/6/6 18:57
 * @description ：
 * @version: 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    //    @Select("select * from user")
    List<User> selectRaw();
}

package com.gxweb.iot.mapper;

import com.gxweb.iot.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (TabUser0)表数据库访问层
 *
 * @author CYQ
 * @since 2020-02-22 23:36:27
 */
@Mapper
public interface UserMapper {

    /**
     * 批量插入
     *
     * @param userEntity
     * @return 插入数量
     */
    @Insert("INSERT INTO `tab_user0`( `name`, `sex`, `age`, `create_time`, `update_time`, `status`) " +
            "VALUES ( #{name}, #{sex},#{age}, now(), now(), #{status})")
    int insertForeach(UserEntity userEntity);

    @Select("select * from tab_user0")
    List<UserEntity> selectAll();
}
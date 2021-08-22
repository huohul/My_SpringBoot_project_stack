package com.xkcoding.orm.mybatis.mapper;

import com.xkcoding.orm.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;

import java.util.List;

/**
 * <p>
 * User Mapper
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-08 10:54
 */
@Mapper

public interface UserMapper {

    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    @Select("SELECT * FROM orm_user")
    List<User> selectAllUser();

    /**
     * 大数量量查询 内存不足 避免OOM
     *
     * @param limit
     * @return
     */
    @Select("select * from orm_user limit #{limit}")
    Cursor<User> scan(@Param("limit") Integer limit);

    /**
     * 根据id查询用户
     *
     * @param id 主键id
     * @return 当前id的用户，不存在则是 {@code null}
     */
    @Select("SELECT * FROM orm_user WHERE id = #{id}")
    User selectUserById(@Param("id") Long id);

    /**
     * 保存用户
     *
     * @param user 用户
     * @return 成功 - {@code 1} 失败 - {@code 0}
     */
    int saveUser(@Param("user") User user);

    /**
     * 删除用户
     *
     * @param id 主键id
     * @return 成功 - {@code 1} 失败 - {@code 0}
     */
    int deleteById(@Param("id") Long id);

}

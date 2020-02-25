package com.gxweb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxweb.entity.SysMenuEntity;
import com.gxweb.entity.SysRoleEntity;
import com.gxweb.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description 系统用户DAO
 * @Author Sans
 * @CreateTime 2019/9/14 15:57
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {

    /**
     * 通过用户ID查询角色集合
     *
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param userId 用户ID
     * @Return List<SysRoleEntity> 角色名集合
     */
    @Select("SELECT sr.* FROM sys_role sr LEFT JOIN sys_user_role sur ON sur.role_id = sr.role_id WHERE sur.user_id = #{userId}")
    List<SysRoleEntity> selectSysRoleByUserId(Long userId);

    /**
     * 通过用户ID查询权限集合
     *
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param userId 用户ID
     * @Return List<SysMenuEntity> 角色名集合
     */
    @Select("SELECT m.* FROM sys_user_role ur LEFT JOIN sys_role_menu rm on  ur.role_id = rm.role_id LEFT JOIN sys_menu m on rm.menu_id= m.menu_id WHERE ur.user_id=#{userId}")
    List<SysMenuEntity> selectSysMenuByUserId(Long userId);
}
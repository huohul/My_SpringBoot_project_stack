package com.gxweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxweb.dao.SysUserDao;
import com.gxweb.entity.SysMenuEntity;
import com.gxweb.entity.SysRoleEntity;
import com.gxweb.entity.SysUserEntity;
import com.gxweb.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/25 17:11
 * @description ：
 * @version: 1.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
    /**
     * 根据用户名查询实体
     *
     * @Author Sans
     * @CreateTime 2019/9/14 16:30
     * @Param username 用户名
     * @Return SysUserEntity 用户实体
     */
    @Override
    public SysUserEntity selectUserByName(String username) {
      /*  QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUserEntity::getUsername, username);
        return this.baseMapper.selectOne(queryWrapper);*/

        return baseMapper.selectOne(new QueryWrapper<SysUserEntity>().lambda().eq(SysUserEntity::getUsername, username));
    }

    /**
     * 通过用户ID查询角色集合
     *
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param userId 用户ID
     * @Return List<SysRoleEntity> 角色名集合
     */
    @Override
    public List<SysRoleEntity> selectSysRoleByUserId(Long userId) {
        return this.baseMapper.selectSysRoleByUserId(userId);
    }

    /**
     * 根据用户ID查询权限集合
     *
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param userId 用户ID
     * @Return List<SysMenuEntity> 角色名集合
     */
    @Override
    public List<SysMenuEntity> selectSysMenuByUserId(Long userId) {
        return this.baseMapper.selectSysMenuByUserId(userId);
    }

}

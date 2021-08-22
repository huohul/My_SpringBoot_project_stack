package com.gxweb.controller;

import com.gxweb.entity.SysMenuEntity;
import com.gxweb.entity.SysRoleEntity;
import com.gxweb.entity.SysUserEntity;
import com.gxweb.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/25 17:27
 * @description ： 链表查询
 * @version: 1.0
 */
@RestController
@RequestMapping
public class SysUserLinked_listController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 根据用户名查询实体
     *
     * @Author Sans
     * @CreateTime 2019/9/14 16:30
     * @Param username 用户名
     * @Return SysUserEntity 用户实体
     */
    @GetMapping("/selectUserByName")
    SysUserEntity selectUserByName(String username) {
        return sysUserService.selectUserByName(username);
    }

    /**
     * 根据用户ID查询角色集合
     *
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param userId 用户ID
     * @Return List<SysRoleEntity> 角色名集合
     */
    @GetMapping("/selectSysRoleByUserId")
    List<SysRoleEntity> selectSysRoleByUserId(Long userId) {
        return sysUserService.selectSysRoleByUserId(userId);
    }

    /**
     * 根据用户ID查询权限集合
     *
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param userId 用户ID
     * @Return List<SysMenuEntity> 角色名集合
     */
    @GetMapping("/selectSysMenuByUserId")
    List<SysMenuEntity> selectSysMenuByUserId(Long userId) {
        return sysUserService.selectSysMenuByUserId(userId);
    }
}

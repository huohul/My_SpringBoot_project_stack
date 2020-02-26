package com.gxweb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import com.gxweb.dao.UserDao;
import com.gxweb.entity.SwaggerUserEntity;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/26 13:44
 * @description ： 用户视图层文档类
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/")
@Api(tags = {"接口分组一"})  //当前类目录折叠总标题
@ApiResponses({@ApiResponse(code = 404, message = "Page Not Found"),
        @ApiResponse(code = 403, message = "Forbidden")})
public class ApiUserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/getAllUser")
    @ApiOperation(value = "获取所有用户", notes = "", httpMethod = "GET")
    public List<SwaggerUserEntity> getAll() {
        return userDao.selectList(new QueryWrapper<>());
    }

    @GetMapping("/getUserById")
    @ApiOperation(value = "根据id获取用户", notes = "id必传", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "用户id", example = "1", required = true, dataType = "long", paramType = "query")
    public SwaggerUserEntity getOne(Long id) {
        return userDao.selectById(id);
    }

    @PostMapping("/getUserByNameAndSex")
    @ApiOperation(value = "根据name和sex获取用户", notes = "", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", example = "关羽", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "userSex", value = "用户性别", example = "男", required = true, dataType = "string", paramType = "query")})
    public List<SwaggerUserEntity> getUserByNameAndSex(String userName, String userSex) {
        SwaggerUserEntity.SwaggerUserEntityBuilder userEntity = SwaggerUserEntity.builder().userName(userName).userSex(userSex);
        QueryWrapper queryWrapper = new QueryWrapper<>().setEntity(userEntity);
        return userDao.selectList(queryWrapper);
    }

    @PostMapping("/insertUser")
    @ApiOperation(value = "新增用户", notes = "传json，数据放body", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "body", value = "用户对象", example = "{userName:'朝雾轻寒',userSex:'男'}", required = true)})
    public String insertUser(@RequestBody SwaggerUserEntity swaggerUserEntity) {
        userDao.insert(swaggerUserEntity);
        return "{code:0,msg:'success'}";
    }

    @PostMapping("/updateUser")
    @ApiOperation(value = "修改用户", notes = "传json，数据放body", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "body", value = "用户对象json", example = "{id:23,userName:'朝雾轻寒',userSex:'女'}", required = true) })
    public String updateUser(@RequestBody SwaggerUserEntity swaggerUserEntity) {
       QueryWrapper<SwaggerUserEntity> queryWrapper =  new QueryWrapper<>();
        queryWrapper.eq("id",swaggerUserEntity.getId());
        userDao.update(swaggerUserEntity, queryWrapper);
        return "{code:0,msg:'success'}";
    }

    @PostMapping("/deleteUser")
    @ApiOperation(value = "删除用户", notes = "id必传", httpMethod = "POST")
    public String deleteUser(@ApiParam(name = "id", value = "用户id", required = true) Long id) {
        userDao.deleteById(id);
        return "{code:0,msg:'success'}";
    }

    /**
     * 分页查询，需先配置分页插件bean，否则分页无效（如有pagehelper需先去除）   http://127.0.0.1:8080/user/selectPage
     * @return
     */

    @RequestMapping(value = "selectPage")
    @ApiOperation(value = "分页查询", notes = "page必传", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", example = "0", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "PageSize", value = "页条数", example = "2", required = true, dataType = "string", paramType = "query")})
    public Page<SwaggerUserEntity> selectPage(Integer pageNum,Integer PageSize) {
        if (StringUtils.isEmpty(PageSize.toString())) pageNum = 0;
        if (StringUtils.isEmpty(PageSize.toString())) PageSize = 2;
        Page<SwaggerUserEntity> page= (Page<SwaggerUserEntity>) userDao.selectPage(new Page<>(pageNum,PageSize), new QueryWrapper<>());
        System.out.println("records:"+page.getRecords());
        return page;
    }

}
package com.gxweb.controller;

import com.gxweb.feign.StuFeign;
import com.gxweb.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author ： CYQ
 * @date ：Created in 2020/02/11 15:14
 * @description ：Stu web层
 * @version: 1.0
 * 坑点: 使用 @Controller 注解不知道怎么回事 能查到 但返回不了页面404 报错    参照畅购 MMP
 *  然后使用 @RestController ok  但是方法参数上不要用@RequestParam和@PathVariable  接收不到参数  MMP
 *  @RequestParam和@PathVariable的区别和使用 :  https://www.cnblogs.com/cherishforchen/p/11402760.html
 */
@RestController
@CrossOrigin
@RequestMapping("/stuApi")
public class StuConterller {

    @Autowired
    private StuFeign stuFeign;

    /**
     * 查询全部数据
     *
     * @return
     */
    @GetMapping("/finAll")
    public Result findAll() {
        Result all = stuFeign.findAll();
        System.out.println(all.getMessage());
        System.out.println(all.getData());
        return all;
    }

    /**
     * 根据id查询 远程feign 调用
     * @param userId
     * @return
     */
    @GetMapping("/getStuid")
    public Result findBuId(Integer userId) {
        Result byId = stuFeign.findById(userId);
        System.out.println(byId.getMessage());
        System.out.println(byId.getData());
        return byId;
    }


}

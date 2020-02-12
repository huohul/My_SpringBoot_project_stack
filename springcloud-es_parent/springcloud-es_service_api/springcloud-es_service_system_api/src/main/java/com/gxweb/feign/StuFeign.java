package com.gxweb.feign;

import com.gxweb.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/11 15:09
 * @description ：
 * @version: 1.0
 */
@FeignClient(name = "system")
@RequestMapping("/student")
public interface StuFeign {

    /**
     * 查询全部数据
     * @return
     */
    @GetMapping
    public Result findAll();

    /***
     * 根据ID查询数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id);


    /***
     * 多条件搜索品牌数据
     * @param searchMap
     * @return
     */
    @GetMapping(value = "/search")
    public Result findList(@RequestParam Map searchMap);

}

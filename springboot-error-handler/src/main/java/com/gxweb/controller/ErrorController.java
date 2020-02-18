package com.gxweb.controller;

import com.gxweb.common.util.error.RandomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/19 0:30
 * @description ：异常视图层
 * @version: 1.0
 */
@RestController
public class ErrorController {

    /**
     * 模拟用户数据访问.
     */
    @GetMapping("/")
    public List index() throws Exception {
        RandomException.randomException(0.75); //随机抛出异常.
        return Arrays.asList("正常用户数据1!", "正常用户数据2! 请按F5刷新!!");
    }

    @GetMapping("/haha")
    public String helo() {
        int i = 10 / 0;
        return "haha";
    }


}

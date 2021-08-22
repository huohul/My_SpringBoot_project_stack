package com.gxweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ： CYQ
 * @date ：Created in 2020/03/13 19:15
 * @description ：
 * @version: 1.0
 */
@Controller
public class IndexController {

    /**
     * 去index 页面
     *
     * @return
     */
    @RequestMapping("/index")
    public String inde(ModelAndView modelAndView) {
        return "index";
    }
}

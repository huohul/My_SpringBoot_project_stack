package com.gxweb.web;

import com.github.pagehelper.PageInfo;
import com.gxweb.dao.UserDao;
import com.gxweb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/25 23:13
 * @description ：分页
 * @version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserPageController {

    @Autowired
    private UserDao userDao;

    // 分页排序
    @RequestMapping("/index")
    public ModelAndView fingByPage(@RequestParam(value = "start", defaultValue = "0") Integer start,
                                   @RequestParam(value = "limit", defaultValue = "10") Integer limit, ModelAndView modelAndView) {
        //分页
        start = start < 0 ? 0 : start;
        Pageable pageRequest = new PageRequest(start, limit);
        Page<User> list = userDao.findAll(pageRequest);

        if (StringUtils.isEmpty(list)) {
            System.out.printf("", "查询消息列表失败");
        }
        System.out.println(list.getContent());
        System.out.println(list.getTotalPages());
        modelAndView.addObject("page", list);
//        modelAndView.addObject("total", list.getTotalElements());
        modelAndView.setViewName("index");
        return modelAndView;
    }

}

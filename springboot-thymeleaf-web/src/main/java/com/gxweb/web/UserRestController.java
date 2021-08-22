package com.gxweb.web;

import com.gxweb.entity.User;
import com.gxweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/07 14:14
 * @description ：用戶控制層
 * @version: 1.0
 */
@Controller
@RequestMapping
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    //范围直接重定向到/list 请求查询所有
//    @RequestMapping("/")
//    public String index() {
//        return "redirect:/list";
//    }

    /**
     * 查询所有
     *
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model) {
        System.out.println("查询所有");
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }

    //跳转到userAdd页面
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    //添加请求  执行完 重定向/list  再次查询所有 包括最新数据
    @RequestMapping("/add")
    public String add(User user) {
        userService.addUser(user);
        System.out.println("新增成功");
        return "redirect:/list";
    }

    //去修改 页面
    @RequestMapping("/toEdit")
    public String toEdit(Model model, long id) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    // 执行修改 请求 执行完重定向到/list  查询最新数据
    @RequestMapping("/edit")
    public String edit(User user) {
        userService.updateUser(user);
        return "redirect:/list";
    }

    //根据id 删除请求 执行完，查询最新数据
    @RequestMapping("/toDelete")
    public String delete(long id) {
        userService.deleteUser(id);
        return "redirect:/list";
    }
}
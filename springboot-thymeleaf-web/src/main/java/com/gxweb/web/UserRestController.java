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
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        System.out.println("查询所有");
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(User user) {
        userService.addUser(user);
        System.out.println("新增成功");
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, long id) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(User user) {
        userService.updateUser(user);
        return "redirect:/list";
    }


    @RequestMapping("/toDelete")
    public String delete(long id) {
        userService.deleteUser(id);
        return "redirect:/list";
    }
}
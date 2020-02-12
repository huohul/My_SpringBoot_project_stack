package com.gxweb.web;


import com.gxweb.pojo.StudentEsDB;
import com.gxweb.service.StuEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author pancm
 * @Title: UserRestController
 * @Description: 用户数据操作接口
 * @Version:1.0.0
 * @date 2018年1月9日
 */
@RestController
@RequestMapping(value = "/api")
public class StuRestController {
    @Autowired
    private StuEsService stuEsService;

    // 新增用户信息  如果参数 没有id信息 则添加500 条数据到es库中 效率较低
    @PostMapping("/user")
    public boolean createUser(@RequestBody StudentEsDB s) {
        return stuEsService.insert(s);
    }

    /**
     * 根据关键字进行全文搜索
     *
     * @param searchContent 搜索内容
     * @return
     */
    @GetMapping("/user/searchContent")
    public List<StudentEsDB> search(@RequestParam(value = "searchContent") String searchContent) {
        return stuEsService.search(searchContent);
    }


    //根据关键字进行搜索并分页
    @GetMapping("/user")
    public List<StudentEsDB> searchUser(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                        @RequestParam(value = "searchContent") String searchContent) {
        return stuEsService.searchUser(pageNumber, pageSize, searchContent);
    }

    //根据关键词权重进行查询 指定为name 属性
    @GetMapping("/user2")
    public List<StudentEsDB> searchUserByWeight(@RequestParam(value = "searchContent") String searchContent) {
        return stuEsService.searchUserByWeight(searchContent);
    }


}

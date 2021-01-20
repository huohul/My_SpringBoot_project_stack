package com.gxweb.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gxweb.domain.Account;
import com.gxweb.service.AccountService;
import com.gxweb.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2021/1/20/020 21:11
 * @description ：控制层
 * @version: 1.0
 */
@RestController
public class RedisController {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private AccountService accountService;

    @RequestMapping("/setAndGet")
    public String test(String k, String v) {
    // 设置缓存有效时间 为10L 以 s 为单位
        redisUtils.set(k, v, 10L);
        return (String) redisUtils.get(k);
    }

    @RequestMapping("/MybatisTest")
    public Object Test() {
        //step1 先从redis中取
        String strJson = (String) redisUtils.get("AccountKey");
        if (strJson == null) {
            System.out.println("从db取值");
            //step2 如果拿不到则从DB取值
            List<Account> accountList = accountService.ListAccount();
            //step3 DB非空的情况刷新redis值
            if (accountList != null) {
                redisUtils.set("AccountKey", JSON.toJSONString(accountList));
                return accountList;
            }
            return null;
        } else {
            System.out.println("从redis缓存取值");
            return JSONObject.parseArray(strJson, Account.class);
        }
    }

}

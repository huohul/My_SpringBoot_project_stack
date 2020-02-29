package com.gxweb.controller;

import com.gxweb.RedissonLock;
import com.gxweb.annotation.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/23 19:29
 * @description ：基于注解的方式 加锁
 * @version: 1.0
 */
@RestController
@RequestMapping
@Slf4j
public class AnnotatinLockController {

    @Autowired
    RedissonLock redissonLock;

    /**
     * 模拟这个是商品库存
     */
    public static volatile Integer TOTAL = 100;

//    @Async(value = "myAsyncPool")
    @GetMapping("annotatin-lock-decrease-stock")
    @DistributedLock(value="goods", leaseTime=5)   //参数 锁名称 锁有效时间   该自定义枚举类 已被 com.oujiong.redisson.annotation.DistributedLockHandler  Redisson分布式锁注解解析器 进行加释放锁
    public String lockDecreaseStock() throws InterruptedException {
        if (TOTAL > 0) {
            TOTAL--;
        }
        log.info("===注解模式=== 减完库存后,当前库存===" + TOTAL);
        return "=================================";
    }

}

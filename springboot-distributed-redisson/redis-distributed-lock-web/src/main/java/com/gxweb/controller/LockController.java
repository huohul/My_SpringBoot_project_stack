package com.gxweb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import com.gxweb.RedissonLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/23 19:57
 * @description ：不基于注解方式锁操作
 * @version: 1.0
 */

@RestController
@Slf4j
public class LockController {
    @Autowired
    RedissonLock redissonLock;

    /**
     * 模拟这个是商品库存
     */
    public static volatile Integer TOTAL = 200;

    //使用jmeter  10000并发 问题出现  要开启多线程 + 并发
    @Async(value = "myAsyncPool")
    @GetMapping("/Unlocked")
    public void Unlocked1() throws InterruptedException {
        if (TOTAL > 0) {
            Thread.sleep(50); //增加点阻碍 比方处理有点慢
            System.out.println("本视图被并发访问对常量进行--操作,常量值;---->" + (TOTAL--));
        } else {
            System.out.println("常量不满足了");
        }
    }


    @Async(value = "myAsyncPool")
    @GetMapping("lock-decrease-stock")
    public String lockDecreaseStock() throws InterruptedException {
        long begindata = System.currentTimeMillis();
        System.out.println("当前开始执行---------------》线程是：" + Thread.currentThread().getName() + "--" + new Snowflake(1, 1).nextId() + "--开始时间：" + DateUtil.date());

        //                  锁名称             锁有效时间
        redissonLock.lock("lock", 10L);
        if (TOTAL > 0) {
            TOTAL--;
        }
//        Thread.sleep(50);    //本来加锁效率就低 就不加休眠了
        log.info("===lock===减完库存后,当前库存===" + TOTAL);
        //如果该线程还持有该锁，那么释放该锁。如果该线程不持有该锁，说明该线程的锁已到过期时间，自动释放锁
        if (redissonLock.isHeldByCurrentThread("lock"))
            redissonLock.unlock("lock");  //解锁 参数 锁key

        System.out.println("当前执行成功---------------》线程是：" + Thread.currentThread().getName() + "--" + new Snowflake(1, 1).nextId() + "--结束时间：" + DateUtil.date() + "---耗时=====》》》》" + (System.currentTimeMillis() - begindata));
        return "操作完成";
    }

    @GetMapping("trylock-decrease-stock")
    public String trylockDecreaseStock() throws InterruptedException {
        //                              锁名称       锁有效时间          等待时间
        if (redissonLock.tryLock("trylock", 5L, 200L)) {
            if (TOTAL > 0) {
                TOTAL--;
            }
            Thread.sleep(50);
            // 解锁   参数 锁名称
            redissonLock.unlock("trylock");
            log.info("====tryLock===减完库存后,当前库存===" + TOTAL);
        } else {
            log.info("[ExecutorRedisson]获取锁失败");
        }
        return "===================================";
    }


}

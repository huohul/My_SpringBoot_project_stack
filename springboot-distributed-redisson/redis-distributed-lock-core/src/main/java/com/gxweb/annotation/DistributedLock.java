package com.gxweb.annotation;

import java.lang.annotation.*;

/**
 * @Description: 基于注解的分布式式锁
 *
 * @author ： CYQ
 * @date ：Created in 2020/02/23 19:29
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DistributedLock {

    /**
     * 锁的名称
     */
    String value() default "redisson";

    /**
     * 锁的有效时间
     */
    int leaseTime() default 10;
}



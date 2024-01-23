package com.cmcc.iot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Administrator
 * @description sentinel
 * https://mp.weixin.qq.com/s/8ICTZRvgJlI5F8gO13JwDA
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SentinelLimiter {

    String resourceName();

    int limitCount() default 50;

}
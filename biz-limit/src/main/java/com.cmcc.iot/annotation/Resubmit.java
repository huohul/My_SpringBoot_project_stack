package com.cmcc.iot.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName Resubmit
 * @Descripition 自定义注解-防重复提交
 * @Author
 * @Date 2023/8/31 10:38
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Resubmit {

    /**
     * 默认过期时间
     * 单位:秒
     *
     * @return
     */
    int value() default 100;

    /**
     * 频繁请求提示语
     *
     * @return
     */
    String messge() default "请求过于频繁,请稍后再试!";

}
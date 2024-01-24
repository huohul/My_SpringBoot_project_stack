package com.gxweb.iot.config;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * 默认情况下，所有的查询都走从库，插入/修改/删除走主库。我们通过方法名来区分操作类型（CRUD）
 * <p>
 * 切面不能建立在DAO层，事务是在service开启的，到dao层再切换数据源，那事务就废了
 */
@Aspect
@Component
public class DataSourceAop {


    /**
     * 第一个”*“符号  表示返回值的类型任意；
     * com.sample.service.impl  AOP所切的服务的包名，即，我们的业务部分
     * 包名后面的”..“  表示当前包及子包
     * 第二个”*“  表示类名，*即所有类。此处可以自定义，下文有举例
     * .*(..)  表示任何方法名，括号表示参数，两个点表示任何参数类型
     * <p>
     * 这是一个切点表达式，它定义了一个切点，该切点在执行以下条件时成立：
     * !@annotation(com.gxweb.iot.config.Master): 这表示切点会排除那些带有@com.gxweb.iot.config.Master注解的方法。
     * execution(* com.gxweb.iot.service.*.select*(..)):
     * 表示切点会包含所有com.lxr.demo.service包下以select开头的方法，并且方法参数可以是任意个数、任意类型。
     * execution(* com.gxweb.iot.service..*.find*(..)):
     * 表示切点会包含所有com.lxr.demo.service包及其子包下以find开头的方法，并且方法参数可以是任意个数、任意类型。
     */
    @Pointcut("!@annotation(com.gxweb.iot.config.Master) " +
            "&& (execution(* com.gxweb.iot.service.*.select*(..)) || execution(* com.gxweb.iot.service..*.find*(..))  ) ")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.gxweb.iot.config.Master) " +
            "|| execution(* com.gxweb.iot.service..*.save*(..)) " +
            "|| execution(* com.gxweb.iot.service..*.add*(..)) " +
            "|| execution(* com.gxweb.iot.service..*.insert*(..)) " +
            "|| execution(* com.gxweb.iot.service..*.update*(..)) " +
            "|| execution(* com.gxweb.iot.service..*.edit*(..)) " +
            "|| execution(* com.gxweb.iot..*.delete*(..)) " +
            "|| execution(* com.gxweb.iot..*.remove*(..))")
    public void writePointcut() {

    }


    /**
     * @param jp
     */
    @Before("readPointcut()")
    public void read(JoinPoint jp) {
        /**
         * JoinPoint对象封装了SpringAop中切面方法的信息,在切面方法中添加JoinPoint参数,就可以获取到封装了该方法信息的JoinPoint对象.
         * 常用api:
         *
         * 方法名  功能
         * Signature getSignature();  获取封装了署名信息的对象,在该对象中可以获取到目标方法名,所属类的Class等信息
         * Object[] getArgs();  获取传入目标方法的参数对象
         * Object getTarget();  获取被代理的对象
         * Object getThis();  获取代理对象
         */
        //获取当前的方法信息
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();//方法头指定修饰符(例如static)、返回值类型、方法名、和形式参数。
        Method method = methodSignature.getMethod();

        //判断方法上是否存在注解@Master
        boolean present = method.isAnnotationPresent(Master.class);//判断注解是否存在该元素上，如果有则返回true，否则false
        if (!present) {
            //如果不存在，默认走从库读
            System.out.println("no");
            DBContextHolder.slave();
        } else {
            //如果存在，走主库读
            System.out.println("yes");
            DBContextHolder.master();
        }
    }


    /**
     *
     */
    @Before("writePointcut()")
    public void write() {
        System.out.println("write");
        DBContextHolder.master();
    }


    /**
     * 另一种写法：if...else...  判断哪些需要读从数据库，其余的走主数据库
     */
//    @Before("execution(* com.cjs.example.service.impl.*.*(..))")
//    public void before(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//
//        if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
//            DBContextHolder.slave();
//        }else {
//            DBContextHolder.master();
//        }
//    }


}
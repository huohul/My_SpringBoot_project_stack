package com.gxweb.redisson.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * spring-boot  使用原生提供的异步任务支持，实现异步执行任务
 *
 * 多线程  异步与同步 执行效率 对比   测试类中得出结果
 * 参考  G:\IDEAGIt\SpringBootAndSpringCloud_The_total_stack\spring-boot-demo\spring-boot-demo-async
 *
 * 还有  新   G:\IDEAGIt\Snailclimb\springboot-guide\source-code\advanced\async-method-springboot
 */
@EnableAsync  //启动异步
@SpringBootApplication
public class SpringbootDemoAsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoAsyncApplication.class, args);
    }

}

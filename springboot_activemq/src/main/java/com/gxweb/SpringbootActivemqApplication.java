package com.gxweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * springboot整合ActiveMQ：基于JMS实现, 比较均衡, 不是最快的, 也不是最稳定的.
 * <p>
 * https://www.cnblogs.com/elvinle/p/8457596.html
 * <p>
 * 注意启动 activeMQ bat  "E:\Program Files\apache-activemq-5.15.10-bin\apache-activemq-5.15.10\bin\win64"   桌面有快捷方式
 * <p>
 * queue 模式 被那个消费 就消失  保留为消费信息
 * <p>
 * topic默认情况下, 是不会保存数据的, 也就是说, consumer是接收不到之前未接收到的信息.  广播模式 兼听者都能收到
 */
@SpringBootApplication
@EnableJms  //开启消息队列
public class SpringbootActivemqApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootActivemqApplication.class, args);
    }

}

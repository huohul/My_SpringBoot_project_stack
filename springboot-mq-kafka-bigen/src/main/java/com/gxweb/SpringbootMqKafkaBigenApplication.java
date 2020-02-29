package com.gxweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot + kafka  实现消息订阅 发送接收 与 发送返回值接收
 *
 * 配置教程 见 E:\桌面\Linux客户端 kafuka.test
 *      Zookeeper 可视化工具插件  Zoolytic-Zookeeper tool
 *      Kafka 可视化管理插件       Kafkalytic
 *
 * 入门改造自 spring-boot-demo 自己的跑不起来  泪崩  有坑
 * 更多 见
 * https://mp.weixin.qq.com/s/tosm_R9-qlipLE2Z50MQ4A
 */
@SpringBootApplication
public class SpringbootMqKafkaBigenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMqKafkaBigenApplication.class, args);
    }

}

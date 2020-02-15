package com.gxweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RabbitMQ  消息发送与接收
 * 介绍：
 *    A_hello  SimpleListener   普通模式  消息发--》一个队列 --》 一个接收
 *
 *    B_work                    工作队列模式  一个发--》  同一个队列 ---》  多个接收者 接收这一个队列  谁抢到就是谁的
 *
 *    C_Broadcastmode           发布订阅 广播模式（fanout）   交换机绑定队列 type:【fanout】  一个交换机 发送给多个绑定存活的队列
 *
 *    D_Routingmode             路由模式（direct）    交换机绑定队列 type:【direct】  一个交换机  发送给多个绑定存活同时绑定路由键（key）的队列
 *
 *    E_Wildcardpattern         通配符模式（topic）    交换机绑定队列 type:【topic】
 *                                                      一个交换机  发送给多个绑定存活同时绑定路径键（key）item.* 只能匹配下一级  item.#  能匹配下数多级 的队列
 */
@SpringBootApplication
public class SpringbootRabbitmqApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitmqApplication.class, args);
    }

}

package com.gxweb.Messagelistener.C_Broadcastmode;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ： CYQ
 * @date ：Created in 2019/07/05 16:55
 * @description ：fanout 广播模式 监听自己的的队列 此模式  交换机配置队列都能接收到消息
 * @version: 1.0
 */

@Component
@RabbitListener(queues = "test2_queue")
public class FonoutListeren2 {

    @RabbitHandler
    public void getMessage(String message) {
        System.out.println("监听队列=====【 二 】======接收到的消息是:" + message);
    }

}

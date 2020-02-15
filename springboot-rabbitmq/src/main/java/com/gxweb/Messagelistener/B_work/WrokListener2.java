package com.gxweb.Messagelistener.B_work;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ： CYQ
 * @date ：Created in 2019/07/05 16:30
 * @description ：队列监听 接收消息
 * @version: 1.0
 */

@Component
@RabbitListener(queues = "work_queue")
public class WrokListener2 {

    @RabbitHandler
    public void getMessage(String message) {
        System.out.println("工作队列模式 监听二 接收到的消息是:" + message);
    }
}

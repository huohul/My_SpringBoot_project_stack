package com.gxweb.Messagelistener.E_Wildcardpattern;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ： CYQ
 * @date ：Created in 2019/07/05 17:42
 * @description ：监听   统配符模式   本监听队列为 * 配 只能一级 接收
 * @version: 1.0
 */

@Component
@RabbitListener(queues = "topic_queue1")
public class TestTopicListeren_jin {

    @RabbitHandler
    public void getMessage(String message) {
        System.out.println("监听被交换机#级匹配队列 接收到的消息是:" + message);
    }
}

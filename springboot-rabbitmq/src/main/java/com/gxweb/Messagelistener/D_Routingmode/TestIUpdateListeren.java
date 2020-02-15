package com.gxweb.Messagelistener.D_Routingmode;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ： CYQ
 * @date ：Created in 2019/07/05 17:21
 * @description ：监听insertKey 队列
 * @version: 1.0
 */

/**
 * 注意路由模式 RabbitListener 参数不是交换机名称 而是对队列名称
 */
@Component
@RabbitListener(queues = "direct_queue_update")
public class TestIUpdateListeren {

    @RabbitHandler
    public void getMessage(String message) {
        System.out.println("路由模式接收指定key 处理不同业务接收到的消息为:" + message);
    }

}

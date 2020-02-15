package com.gxweb.Messagelistener.A_hello;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author ： CYQ
 * @date ：Created in 2019/07/05 16:06
 * @description ：简单模式  服务消费者   Serving consumers
 * @version: 1.0
 * <p>
 * RabbitListener(queues="") 参数 为 队列名
 */
@Slf4j
@Component
@RabbitListener(queues = "Simple_queue")
public class SimpleListener {
    /**
     * 如果 spring.rabbitmq.listener.direct.acknowledge-mode: auto，则可以用这个方式，会自动ack
     */
//    @RabbitHandler
    public void listenerTest(String message) {
        System.out.println("Simple简单模式消费者接收到的消息是:" + message);
        log.info("直接队列处理器，接收消息：{}", JSONUtil.toJsonStr(message));
    }

    @RabbitHandler
    public void directHandlerManualAck(String messageStruct, Message message, Channel channel) {
        //  如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("直接队列1，手动ACK，接收消息：{}", JSONUtil.toJsonStr(messageStruct));
            // 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                // 处理失败,重新压入MQ
                channel.basicRecover();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }


}

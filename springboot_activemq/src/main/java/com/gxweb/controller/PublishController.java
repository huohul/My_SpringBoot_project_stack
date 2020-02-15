package com.gxweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * 消息生产者
 */

@RestController
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    private JmsMessagingTemplate jms;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @RequestMapping("/queue")
    public String queue() {
        for (int i = 0; i < 10; i++) {
            jms.convertAndSend(queue, "queue" + i);
        }

        return "queue 发送成功";
    }

    //com.gxweb.listener.QueueListener  注解调用 得到消费者接收到的消息
    @JmsListener(destination = "out.queue")
    public void consumerMsg(String msg) {
        System.out.println("得到消费者的返回值是---------------" + msg);
    }

    @RequestMapping("/topic")
    public String topic() {
        for (int i = 0; i < 10; i++) {
            jms.convertAndSend(topic, "topic" + i);
        }
        return "topic 发送成功";
    }
}
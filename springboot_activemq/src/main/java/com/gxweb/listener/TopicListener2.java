package com.gxweb.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class TopicListener2 {
                                //队列名 yml有配置
    @JmsListener(destination = "publish.topic", containerFactory = "jmsListenerContainerTopic")
    public void receive(String text) {
        System.out.println("TopicListener: consumer-2 收到一条信息: " + text);
    }
}
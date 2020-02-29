package com.gxweb.handler;

import com.gxweb.constants.KafkaConsts;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 消息处理器
 * </p>
 */
@Component
@Slf4j
public class MessageHandler {

    @KafkaListener(topics = KafkaConsts.TOPIC_TEST, containerFactory = "ackContainerFactory")
    public void handleMessage(ConsumerRecord record, Acknowledgment acknowledgment) {
        try {
            String message = (String) record.value();
            log.info("TOPIC_TEST收到消息: {}", message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaConsts.TOPIC_1, containerFactory = "ackContainerFactory")
    public void handleMessage1(ConsumerRecord record, Acknowledgment acknowledgment) {
        try {
            String message = (String) record.value();
            log.info("TOPIC_1收到消息: {}", message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaConsts.TOPIC_2, containerFactory = "ackContainerFactory")
    public void handleMessage2(ConsumerRecord record, Acknowledgment acknowledgment) {
        try {
            String message = (String) record.value();
            log.info("TOPIC_2收到消息: {}", message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }
}

package com.gxweb.controller;

import com.gxweb.constants.KafkaConsts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/29 0:00
 * @description ：
 * @version: 1.0
 */
@RestController
public class Sentest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 测试发送消息
     */
    @GetMapping("/sendTestmess")
    public void testSend() {
        kafkaTemplate.send(KafkaConsts.TOPIC_TEST, "hello,kafka...");
    }


    //    获取发送结果 异步获取
    @GetMapping("/sendAnsyc")
    public void sendAnsyc(String mes) {
        kafkaTemplate.send(KafkaConsts.TOPIC_1, mes).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
//                result.getProducerRecord().topic();
//                result.getProducerRecord().key();
//                result.getRecordMetadata();
                System.out.println("发送消息成功：" + result);
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("发送消息失败：" + ex.getMessage());
            }
        });
    }

    // 同步
    @GetMapping("/sendSync")
    public void sendSync(String bar) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(KafkaConsts.TOPIC_2, bar);
        try {
            SendResult<String, String> result = future.get();
            System.out.println("同步发送成功返回消息："+result);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}

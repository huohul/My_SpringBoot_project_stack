package com.gxweb.conterller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/15 18:37
 * @description ：消息提供者 生产者
 * @version: 1.0
 */
@RestController
@RequestMapping("/pai")
public class MessageProviders {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 最少两个参数 第一个参数为队列名 第二个参数 发送内容
     */
    @RequestMapping("/A")
    public void simpleTestA() {
        rabbitTemplate.convertAndSend("Simple_queue", "你好我是第一个简单模式");
    }

    /**
     * 工作队列模式 多个接收方抢着 接收 用于 处理任务较多 提高性能
     */
    @RequestMapping("/B")
    public void sendMessageB() {
        for (int i = 0; i < 1000; i++) {
            rabbitTemplate.convertAndSend("work_queue", i + "===工作队列模式:Work Queues与入门程序的简单模式相比，多了一个或一些消费端，多个消费端共同消费同一个队列中的消息");
        }
    }

    /**
     * 发布订阅模式之  广播模式 fanout 交换机 分发给所有存活队列
     * 发送消息
     * 参数一 交换机名
     * 参数二 路由模式 綁定  参数 这里不写
     * 参数三 要发送的内容
     */
    @RequestMapping("/C")
    public void sendFaout() {
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend("fanout_exchange", "", "消息订阅模式之 ===Fanout：广播  将消息交给所有绑定到交换机的队列, 不处理路由键。只需要简单的将队列绑定到交换机上。fanout 类型交换机转发消息是最快的。");
        }
    }

    /**
     * 路由模式 ：队列与交换机的绑定，不能是任意绑定了，而是要指定一个RoutingKey（路由key）消息的发送方在向 Exchange发送消息时，
     * 也必须指定消息的RoutingKey。Exchange不再把消息交给每一个绑定的队列，而是根据消息的Routing Key进行判断，只有队列的Routingkey与消息的Routing key完全一致，才会接收到消息.
     * 参数一 交换机 名称
     * 参数二 交换机 绑定队列的自动义 key  就可以将不同业务发给处理该业务的队列
     * 参数三  要发送的内容
     */
    @RequestMapping("/D")
    public void sendMessageD() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                rabbitTemplate.convertAndSend("direct_exchange", "insertKey", "Insert=========routing路由模式: 一个交换器, 交换器类型必须设置为Direct定向发送模式, 在交换器中设置不同的路由规则也就是Routing key,\n" +
                        "\t\t\t根据不同的路由规则绑定不同的队列,  然后在代码中发送方将数据发往同一个交换器, 但是可以根据业务进行判断,\n" +
                        "\t\t\t设置不同的路由键routingKey, 然后消息服务器中的交换器, 接收到数据后, 会根据不同的路由规则发往不同的队列");
            } else {
                rabbitTemplate.convertAndSend("direct_exchange", "updateKey", "Update ======routing路由模式: 一个交换器, 交换器类型必须设置为Direct定向发送模式, 在交换器中设置不同的路由规则也就是Routing key,\n" +
                        "\t\t\t根据不同的路由规则绑定不同的队列,  然后在代码中发送方将数据发往同一个交换器, 但是可以根据业务进行判断,\n" +
                        "\t\t\t设置不同的路由键routingKey, 然后消息服务器中的交换器, 接收到数据后, 会根据不同的路由规则发往不同的队列");
            }
        }
    }

    /**
     * Topics通配符模式(主题模式)
     */
    @RequestMapping("/E")
    public void sendMessageE() {
        rabbitTemplate.convertAndSend("topic_exchange", "item.select", "* 匹配 只能下一级匹配 ==Topic类型与Direct相比，都是可以根据RoutingKey把消息路由到不同的队列。只不过Topic类型Exchange可以让队列在绑定Routing key的时候使用通配符！ ");
        rabbitTemplate.convertAndSend("topic_exchange", "item.select.count", "# 匹配 能多级匹配 ==Topic类型与Direct相比，都是可以根据RoutingKey把消息路由到不同的队列。只不过Topic类型Exchange可以让队列在绑定Routing key的时候使用通配符！ ");

    }


}

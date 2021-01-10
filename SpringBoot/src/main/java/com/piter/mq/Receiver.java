package com.piter.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * 定义该类需要监听的队列
 */
@Component
@RabbitListener(queues = "testRabbit")
public class Receiver {

    /**
     * 对指定消息的处理
     * @param msg
     */
    @RabbitHandler
    public void process(String msg) {
        System.out.println("receive msg :" + msg);
    }

}

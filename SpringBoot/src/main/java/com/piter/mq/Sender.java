package com.piter.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 生产者
 * 向队列里发送一个消息
 */
@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        rabbitTemplate.convertAndSend("testRabbit", "this a message!");
    }
}

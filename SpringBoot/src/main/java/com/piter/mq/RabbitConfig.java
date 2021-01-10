package com.piter.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 创建一个队列
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue firstQueue(){
        return new Queue("testRabbit");
    }

}

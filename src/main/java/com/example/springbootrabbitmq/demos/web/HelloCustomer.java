package com.example.springbootrabbitmq.demos.web;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: RabbitMQ
 * @description:
 * @author:
 * @create: 2023-12-08 17:10
 **/

@Component
@RabbitListener(queuesToDeclare = @Queue("hello"))
public class HelloCustomer {
    @RabbitHandler
    public void read(String message) {
        System.out.println("message : " + message);
    }
}

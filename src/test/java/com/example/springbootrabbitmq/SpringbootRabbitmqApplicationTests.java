package com.example.springbootrabbitmq;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = SpringbootRabbitmqApplication.class)
@RunWith(SpringRunner.class)
class SpringbootRabbitmqApplicationTests {

    @Autowired
    private RabbitTemplate rabbitmqTemplate;

    @Test
    public void contextLoads() {
        rabbitmqTemplate.convertAndSend("hello", "hello world");
    }

}

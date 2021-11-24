package com.rabbitmq.cloud;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class MqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqConsumerApplication.class, args);
    }

}

package com.rabbitmq.cloud.controller;

import com.fasterxml.jackson.core.JsonProcessingException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class SendMessage {
    private final String EXCHANGE_DIRECT_NAME="direct_exchange";
    private final String ROUTING_KEY_DIRECT_NAME = "direct_routingkey";
    private final String ROUTING_KEY_DIRECT_NAME1 = "direct_routingkey_two";
    private final String QUEUE_DIRECT_NAME1="direct_queue_two";
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private static final Logger logger = LoggerFactory.getLogger(SendMessage.class);

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() throws JsonProcessingException {
        logger.info("1231231");
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        //UserInfo userInfo = new UserInfo("wengdaxia","zhenniubi");
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        //map.put("userinfo",userInfo);
        //map.put("user",userInfo);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        /**
         * String exchange, 交换机名称
         * String routingKey, routingkey
         * Object object,消息
         * @Nullable CorrelationData correlationData
         */

        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(EXCHANGE_DIRECT_NAME,ROUTING_KEY_DIRECT_NAME1 , "userInfo");
        }

        return "ok";
    }

}


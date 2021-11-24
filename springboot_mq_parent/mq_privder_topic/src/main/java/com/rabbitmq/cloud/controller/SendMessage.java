package com.rabbitmq.cloud.controller;
import com.rabbitmq.cloud.dto.ClassRoom;
import com.rabbitmq.cloud.dto.UserInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
public class SendMessage {
    //定义交换机名称
    private final String exchange_topic_name="topic_exchange_test";

    private final String exchange_fanout_name = "queue.fanout.exchange";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage()  {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        List<ClassRoom> list = new ArrayList<>();
        ClassRoom classRoom = new ClassRoom("三班",1);
        list.add(classRoom);
        UserInfo userInfo = new UserInfo("wengdaxia","zhenniubi",list);
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        map.put("userinfo",userInfo);
        //map.put("user",userInfo);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        /**
         * String exchange, 交换机名称
         * String routingKey, routingkey
         * Object object,消息
         * @Nullable CorrelationData correlationData
         */

        for (int i = 0; i < 5; i++) {
            rabbitTemplate.convertAndSend(exchange_topic_name,"topic.898" , userInfo);
        }

        return "ok";
    }

    /**
     * 扇形交换机发送信息
     */
    @GetMapping("/sendFanoutMessage")
    public String sendFanoutMessage()  {
        List<ClassRoom> list = new ArrayList<>();
        ClassRoom classRoom = new ClassRoom("三班",1);
        list.add(classRoom);
        UserInfo userInfo = new UserInfo("wengdaxia","zhenniubi",list);
        for (int i = 0; i < 5; i++) {
            rabbitTemplate.convertAndSend(exchange_fanout_name,null,userInfo);
            System.out.println("生产者第"+i+"次发送消息=>");
        }

        return "ok";
    }
}


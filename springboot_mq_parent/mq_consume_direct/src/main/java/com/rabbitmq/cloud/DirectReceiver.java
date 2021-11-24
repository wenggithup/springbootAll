package com.rabbitmq.cloud;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
//监听队列的名称

public class DirectReceiver {
    private final String QUEUE_DIRECT_NAME="direct_queue_one";
    private final String QUEUE_DIRECT_NAME1="direct_queue_two";

    private final String exchange_topic_name="topic_exchange_test";
    //定义队列名称
    private final String queue_topic_name_email = "email.topic";
    private final String queue_topic_name_qq = "qq.topic";

    private final String queue_fanout_name_qq = "queue.fanout.qq";
    private final String queue_fanout_name_wechat= "queue.fanout.wechat";
    private final String queue_fanout_name_dingding = "queue.fanout.dingding";

/*
    @RabbitHandler
    //消费者在此绑定交换机
    @RabbitListener(queues = QUEUE_DIRECT_NAME1)
    //@Payload 注入消息体到一个JavaBean中
    public void process(String userInfo) throws JsonProcessingException {

        System.out.println("DirectReceiver消费者收到消息  : " +new Date());

    }
*/

/*    @RabbitHandler
    //消费者在此绑定交换机
    @RabbitListener(queues = queue_topic_name_email)
    //@Payload 注入消息体到一个JavaBean中
    public void process_queue_topic_name_email(String userInfo) throws JsonProcessingException {

        System.out.println("queue_topic_name_email消费者收到消息  : " + userInfo.getUsername()+"---"+userInfo.getPassword());
    }

    @RabbitHandler
    //消费者在此绑定交换机
    @RabbitListener(queues = queue_topic_name_qq)
    //@Payload 注入消息体到一个JavaBean中
    public void process_queue_topic_name_qq(@Payload UserInfo userInfo) throws JsonProcessingException {

        System.out.println("queue_topic_name_qq消费者收到消息  : " + userInfo.getUsername()+"---"+userInfo.getPassword());
    }

    @RabbitHandler
    //消费者在此绑定交换机
    @RabbitListener(queues = queue_fanout_name_qq)
    //@Payload 注入消息体到一个JavaBean中
    public void queue_fanout_name_qq(@Payload UserInfo map) throws JsonProcessingException {
        ClassRoom classRoom = map.getList().get(0);
        System.out.println(classRoom.getClassId()+"---"+classRoom.getClassName());
        System.out.println("queue_topic_name_qq消费者收到消息  : " + map.getUsername()+"---"+map.getPassword());
    }*/

}

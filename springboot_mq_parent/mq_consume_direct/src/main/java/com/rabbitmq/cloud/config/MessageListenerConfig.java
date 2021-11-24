package com.rabbitmq.cloud.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DATE: 2021/4/5 9:56 下午
 * Author: WengChuanJie
 * 手动确认消息处理类
 */
@Configuration
public class MessageListenerConfig {
    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    private MyAckReceiver myAckReceiver;//消息接收处理类
    private final String QUEUE_DIRECT_NAME1="direct_queue_two";

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        //当前消费者数量
        container.setConcurrentConsumers(10);
        //最大消费者数量
        container.setMaxConcurrentConsumers(10);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // RabbitMQ默认是自动确认，这里改为手动确认消息
        //设置一个队列
        container.setQueueNames(QUEUE_DIRECT_NAME1);
        //如果同时设置多个如下： 前提是队列都是必须已经创建存在的
        //  container.setQueueNames("TestDirectQueue","TestDirectQueue2","TestDirectQueue3");
        //另一种设置队列的方法,如果使用这种情况,那么要设置多个,就使用addQueues
        //container.setQueues(new Queue("TestDirectQueue",true));
        //container.addQueues(new Queue("TestDirectQueue2",true));
        //container.addQueues(new Queue("TestDirectQueue3",true));


        //后置处理器，接收到的消息都添加了Header请求头
        /* container.setAfterReceivePostProcessors(message -> {
            message.getMessageProperties().getHeaders().put("desc",10);
            return message;});*/
        container.setMessageListener(myAckReceiver);

        return container;
    }


}

package com.rabbitmq.cloud.config;

import com.rabbitmq.client.Channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * DATE: 2021/4/5 9:57 下午
 * Author: WengChuanJie
 */
@Component
public class MyAckReceiver implements ChannelAwareMessageListener {
    private static  int count;
    private Logger logger = LoggerFactory.getLogger(MyAckReceiver.class);
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long tag = message.getMessageProperties().getDeliveryTag();
        String msg = message.toString();
        String[] split = msg.split("'");
        String s = split[1];

        System.out.println(s+"==========="+Thread.currentThread().getName());
        count++;
        System.out.println(count);
        channel.basicAck(tag,true);


    }
}

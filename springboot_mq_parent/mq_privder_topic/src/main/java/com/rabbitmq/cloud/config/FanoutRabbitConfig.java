package com.rabbitmq.cloud.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 扇形交换机配置文件
 */
@Configuration
public class FanoutRabbitConfig {
    //定义队列名称
    private final String queue_fanout_name_qq = "queue.fanout.qq";
    private final String queue_fanout_name_wechat= "queue.fanout.wechat";
    private final String queue_fanout_name_dingding = "queue.fanout.dingding";

    //定义交换机名称
    private final String exchange_fanout_name = "queue.fanout.exchange";

    //声明队列
    @Bean
    public Queue getQueue_fanout_name_qq(){
        return new Queue(queue_fanout_name_qq,true);
    }

    @Bean
    public Queue getQueue_fanout_name_wechat(){
        return new Queue(queue_fanout_name_wechat,true);
    }

    @Bean
    public Queue getQueue_fanout_name_dingding(){
        return new Queue(queue_fanout_name_dingding,true);
    }

    //声明扇形交换机
    @Bean
    public FanoutExchange getExchange_fanout_name(){
        return new FanoutExchange(exchange_fanout_name,true,false,null);
    }
    //将队列绑定至交换机
    @Bean
    public Binding getBinding(){
        return BindingBuilder.bind(getQueue_fanout_name_qq()).to(getExchange_fanout_name());
    }

    @Bean
    public Binding getBinding1(){
        return BindingBuilder.bind(getQueue_fanout_name_wechat()).to(getExchange_fanout_name());
    }

    @Bean
    public Binding getBinding2(){
        return BindingBuilder.bind(getQueue_fanout_name_dingding()).to(getExchange_fanout_name());
    }
}

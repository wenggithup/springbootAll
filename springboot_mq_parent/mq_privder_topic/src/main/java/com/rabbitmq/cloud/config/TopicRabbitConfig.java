package com.rabbitmq.cloud.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {
    //定义交换机名称
    private final String exchange_topic_name="topic_exchange_test";
    //定义队列名称
    private final String queue_topic_name_email = "email.topic";
    private final String queue_topic_name_qq = "qq.topic";
    @Bean
    public Queue TopicQueueEmail(){

        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue(queue_topic_name_email,true);
    }
    @Bean
    public Queue TopicQueueQq(){

        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue(queue_topic_name_qq,true);
    }

    //声明交换机
    /**
     * CustomExchange
     * DirectExchange :直连型（routing）交换机
     * FanoutExchange ：广播（订阅）交换机
     * HeadersExchange ：hdeader交换机
     * TopicExchange.class ：通配符交换机
     *
     * String name, 交换机名称
     * boolean durable,是否持久化
     * boolean autoDelete,是否自动删除
     * Map<String, Object> arguments 拓展参数
     */
    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange(exchange_topic_name,true,false,null);
    }
    @Bean
    //将队列绑定至交换机
    public Binding getBindingTopic(){
        return BindingBuilder.bind(TopicQueueEmail()).to(getTopicExchange()).with("topic.email.#");
    }

    @Bean
    //将队列绑定至交换机
    public Binding getBindingTopic1(){
        return BindingBuilder.bind(TopicQueueQq()).to(getTopicExchange()).with("topic.#");
    }
}

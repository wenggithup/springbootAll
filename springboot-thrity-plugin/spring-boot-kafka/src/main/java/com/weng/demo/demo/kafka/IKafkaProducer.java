package com.weng.demo.demo.kafka;

import com.weng.demo.demo.bean.KafkaMessage;

import java.util.concurrent.ExecutionException;

/**
 * @DATE: 2022/4/7 4:24 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public interface IKafkaProducer {

    /**
     * 推送消息,有回调
     * @param kafkaMessage
     */
    void push(KafkaMessage kafkaMessage,KafkaCallBack kafkaCallBack);

    /**
     * 推送消息，无回调
     * @param kafkaMessage
     */
    void push(KafkaMessage kafkaMessage);
}

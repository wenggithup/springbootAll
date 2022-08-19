package com.weng.demo.demo.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @DATE: 2022/4/7 4:36 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public interface KafkaCallBack {


    void callback(RecordMetadata recordMetadata, Exception e);
}

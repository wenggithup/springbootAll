package com.weng.demo.demo.kafka;

import com.weng.demo.demo.kafka.producer.KafkaProducerHelper;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @DATE: 2022/4/7 8:06 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class RetriesKafkaCallBack implements Callback {
    private ConcurrentMap<String,Integer> map = new ConcurrentHashMap<>();

    private KafkaProducerHelper helper;


    /**
     * 回调重试，超过三次将消息丢弃
     * @param recordMetadata
     * @param e
     */
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
    //发生了异常
        if (null != e){
            Integer count = map.get(recordMetadata.toString());
            count = count == null?1:count+1;
            if (count >3){
                //TODO 直接将消息丢出队列
                return;
            }

            map.putIfAbsent(recordMetadata.toString(),count);
            //重新放回队列

        }
    }
}

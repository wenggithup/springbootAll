package com.weng.demo.demo.kafka.producer;

import com.weng.demo.demo.bean.KafkaMessage;
import com.weng.demo.demo.kafka.IKafkaProducer;
import com.weng.demo.demo.kafka.KafkaCallBack;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @DATE: 2022/4/7 4:10 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class KafkaProducerHelper implements IKafkaProducer {
    private KafkaProducer<Long, String> producer = KafkaProducerConfig.producer;

    public KafkaProducerHelper(){

    }


    @Override
    public void push(KafkaMessage kafkaMessage,KafkaCallBack kafkaCallBack) {
        String topic = kafkaMessage.getTopic();
        if (null != topic && "" !=topic) {
            ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(topic, kafkaMessage.getData());
            //设置回调
            if (null != kafkaCallBack){
            producer.send(record,(d,e)->kafkaCallBack.callback(d,e));
            }else {
                producer.send(record);
            }

        }

    }

    /**
     * 无回调推送消息
     * @param kafkaMessage
     */
    @Override
    public void push(KafkaMessage kafkaMessage) {
        this.push(kafkaMessage,null);
    }


}

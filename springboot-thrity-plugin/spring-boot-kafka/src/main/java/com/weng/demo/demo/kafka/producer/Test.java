package com.weng.demo.demo.kafka.producer;

import com.weng.demo.demo.bean.KafkaMessage;
import com.weng.demo.demo.bean.KafkaSettings;
import com.weng.demo.demo.kafka.KafkaCallBack;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.ExecutionException;


/**
 * @DATE: 2022/4/7 5:06 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class Test {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        KafkaSettings settings = new KafkaSettings();
        settings.setServers("192.168.6.84:21090");
        KafkaProducerConfig config = new KafkaProducerConfig(settings);
        KafkaProducerHelper helper = new KafkaProducerHelper();
        KafkaMessage kafkaMessage = new KafkaMessage();

        for (int i = 0; i < 1; i++) {

            kafkaMessage.setTopic("test_topic_ex");
            kafkaMessage.setData("1234234234234234sada"+i);

            //helper.push(kafkaMessage);
            helper.push(kafkaMessage);


        }

        Thread.sleep(500*60);
    }

}

package com.weng.demo.demo.kafka.producer;

import com.weng.demo.demo.bean.KafkaSettings;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @DATE: 2022/4/7 8:40 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class KafkaProducerConfig {
    public static KafkaProducer<Long, String> producer;

    public KafkaProducerConfig(KafkaSettings settings) {
        if (producer == null) {
            synchronized (this) {
                if (producer == null) {
                    this.producer = initKafka(settings);
                }
            }
        }
    }


    private KafkaProducer<Long, String> initKafka(KafkaSettings settings) {
        String server = settings.getServers();

        Properties props = new Properties();

        props.put("bootstrap.servers", server);


        // key序列化
        props.put("key.serializer", StringSerializer.class);

        props.put("value.serializer", StringSerializer.class);
        // 重试次数
        props.put("retries", 3);
        KafkaProducer<Long, String> producer = new KafkaProducer<Long, String>(props);
        return producer;
    }
}

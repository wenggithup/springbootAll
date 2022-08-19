package com.weng.demo.demo.bean;

/**
 * @DATE: 2022/4/7 4:25 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class KafkaMessage {
    public KafkaMessage(String topic, String data) {
        this.topic = topic;
        this.data = data;
    }

    public KafkaMessage() {
    }

    private String topic;

    private String data;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

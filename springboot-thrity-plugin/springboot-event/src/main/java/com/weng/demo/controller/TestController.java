package com.weng.demo.controller;

import com.weng.demo.event.MyCustomizeEvent;
import com.weng.demo.event.PushEventProducer;
import com.weng.demo.listener.MyCustomizeListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @DATE: 2022/7/28 10:34 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@RestController
public class TestController {
    @Resource
    MyCustomizeListener myCustomizeListener;

    @PostConstruct
    public void init() {
        myCustomizeListener.setStr("213123");
    }

    @Resource
    private ApplicationContext producer;

    @GetMapping("/testEvent")
    public void testEvent() {
        producer.publishEvent(new MyCustomizeEvent("123123"));
    }
}

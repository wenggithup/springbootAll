package com.weng.demo.event;

import org.springframework.context.ApplicationEvent;

/**
 * @DATE: 2022/7/28 10:28 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class MyCustomizeEvent extends ApplicationEvent {
    public MyCustomizeEvent(Object source) {
        super(source);
        System.out.println(source.toString());
    }

}

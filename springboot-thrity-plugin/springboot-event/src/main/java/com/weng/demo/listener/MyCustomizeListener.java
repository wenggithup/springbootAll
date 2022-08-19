package com.weng.demo.listener;

import com.weng.demo.event.MyCustomizeEvent;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @DATE: 2022/7/28 10:31 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Component
public class MyCustomizeListener implements ApplicationListener<MyCustomizeEvent>, ApplicationContextAware {
    private String str;
    private ApplicationContext applicationContext;
    public void setStr(String str){
        this.str = str;
        System.out.println("set str = "+str);
        System.out.println(this.toString());
    }

    @Override
    public void onApplicationEvent(MyCustomizeEvent event) {
        System.out.println("正在监听事件。。。。。    str ="+ str);

        System.out.println(this.toString());
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }
    private static AtomicInteger integer = new AtomicInteger(0);
    public MyCustomizeListener() {
        integer.incrementAndGet();
        System.out.println("构造器执行了 "+integer);
    }

}

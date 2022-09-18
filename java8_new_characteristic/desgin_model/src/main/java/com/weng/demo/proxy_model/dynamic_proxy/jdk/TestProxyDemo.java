package com.weng.demo.proxy_model.dynamic_proxy.jdk;

import java.lang.reflect.InvocationTargetException;

/**
 * @DATE: 2022/9/8 10:18 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class TestProxyDemo {

    /**
     * jdk动态代理，必须是面向接口，目标业务类必须实现接口
     * @param args
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Vehicle car = DemoProxyFactory.newInstance(Bmw.class);
        Config config = DemoProxyFactory.newInstance(Bmw.class);
        config.config();
        car.stop();
    }


   static abstract class Car{
        void run(){
            System.out.println("car is r");
        };
    }

   public static class Bmw extends Car implements Vehicle,Config{

        @Override
        public void run() {
            System.out.println("Bmw is running...");
        }

       @Override
       public void stop() {
           System.out.println("Bmw is stopping...");
       }

       @Override
       public void config() {
           System.out.println("bmv config....");
       }
   }

    interface Vehicle{
        void stop();
    }

    interface Config{
        void config();
    }
}

package com.weng.demo.proxy_model.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @DATE: 2022/9/7 6:00 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class JdkDynamicProxy {

    public static void main(String[] args) {
        /**
         * ClassLoader loader, 用哪个类加载器去加载代理对象
         * Class<?>[] interfaces, 动态代理类需要实现的接口
         * InvocationHandler h h:动态代理方法在执行时，会调用h里面的invoke方法去执行
         */
        Car car = new Bmw();
        Class [] interfaces = new Class<?>[]{Car.class};
        Car o =(Car) Proxy.newProxyInstance(JdkDynamicProxy.class.getClassLoader(), interfaces, new ProxyHandler(car));
        o.run();
//        System.out.println(o.toString());

    }


    interface Car{
        void run();
    }

    static class Bmw implements Car{

        @Override
        public void run() {
            System.out.println("Bmw is running...");
        }
    }

    static class ProxyHandler implements InvocationHandler{
        private Object target;
        public ProxyHandler(Object o){
            this.target = o;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("代理执行前。。。。");
            Object invoke = method.invoke(target, args);
            System.out.println("代理执行后。。。。");
//            System.out.println(invoke.toString());
            System.out.println(invoke);
            return invoke;


        }
    }
}

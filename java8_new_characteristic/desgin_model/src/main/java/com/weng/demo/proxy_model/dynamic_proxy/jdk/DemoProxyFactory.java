package com.weng.demo.proxy_model.dynamic_proxy.jdk;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * @DATE: 2022/9/8 3:04 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:代理工厂
 */
public class DemoProxyFactory {

    public static <T> T newInstance(Class<T> tClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?>[] interfaces = tClass.getInterfaces();

        Constructor<T> constructor = tClass.getConstructor();
        T t = constructor.newInstance();

        DemoProxyHandler proxyHandler = new DemoProxyHandler(t);

        Object o = Proxy.newProxyInstance(DemoProxyFactory.class.getClassLoader(), interfaces, proxyHandler);

        return (T)o;
    }
}

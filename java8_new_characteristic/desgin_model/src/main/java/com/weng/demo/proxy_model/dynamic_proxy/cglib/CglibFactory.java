package com.weng.demo.proxy_model.dynamic_proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @DATE: 2022/9/14 7:35 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class CglibFactory<T> implements InvocationHandler {

    private Class<T> aClass;

    public CglibFactory(Class<T> t){
        this.aClass = t;
    }

    /**
     * static 和 final 类不能进行代理。如果构造器私有，也不能进行代理
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T createCglibProxy(Class<T> tClass){
        try {
            Constructor<T> constructor = tClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            T t = constructor.newInstance();

            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(t.getClass());
            enhancer.setCallback(new CglibFactory<T>(tClass));
            return(T)enhancer.create();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }




//    @Override
//    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//        Constructor<T> constructor = aClass.getConstructor();
//        T t = constructor.newInstance();
//        System.out.println("方法执行前。。。");
//        Object invoke = method.invoke(t, objects);
//        System.out.println("方法执行后。。。");
//
//        return invoke;
//    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Constructor<T> constructor = aClass.getConstructor();
        T t = constructor.newInstance();
        System.out.println("方法执行前。。。");
        Object invoke = method.invoke(t, objects);
        System.out.println("方法执行后。。。");

        return invoke;
    }
}

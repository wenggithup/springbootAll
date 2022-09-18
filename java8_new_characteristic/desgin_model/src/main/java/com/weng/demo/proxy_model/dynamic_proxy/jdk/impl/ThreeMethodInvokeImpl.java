package com.weng.demo.proxy_model.dynamic_proxy.jdk.impl;

import com.weng.demo.proxy_model.dynamic_proxy.jdk.DemoProxyHandleInvoke;
import com.weng.demo.proxy_model.dynamic_proxy.jdk.DemoServerMethodInvoke;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @DATE: 2022/9/8 9:59 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class ThreeMethodInvokeImpl implements DemoServerMethodInvoke {
    @Override
    public int getOrder() {
        return 1245;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, DemoProxyHandleInvoke proxyHandleInvoke) throws InvocationTargetException, IllegalAccessException {
        System.out.println("这是 ThreeMethodInvokeImpl.... ");
        // 方法名
        String methodName = method.getName();

        System.out.println(methodName);
        // 方法所属类名
        String className = method.getDeclaringClass().getName();
        System.out.println(className);
        System.out.println("ThreeMethodInvokeImpl 执行代理方法之前。。。。。");
        //执行代理方法
        Object result = proxyHandleInvoke.invoke(proxy,args);
//        System.out.println(result.toString());
        System.out.println("ThreeMethodInvokeImpl 执行代理方法结束。。。。。");
        return result;
    }
}

package com.weng.demo.proxy_model.dynamic_proxy.jdk;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @DATE: 2022/9/8 9:42 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:自定义拦截器链
 */
public interface DemoServerMethodInvoke {

    /**
     * 拦截器排序
     * @return
     */
    default int getOrder(){
        return 999;
    };

    /**
     * 自定义拦截器链执行逻辑
     * @param proxy
     * @param method
     * @param args
     * @param proxyHandleInvoke
     * @return
     */
    Object intercept(Object proxy, Method method, Object[] args, DemoProxyHandleInvoke proxyHandleInvoke) throws InvocationTargetException, IllegalAccessException;
}

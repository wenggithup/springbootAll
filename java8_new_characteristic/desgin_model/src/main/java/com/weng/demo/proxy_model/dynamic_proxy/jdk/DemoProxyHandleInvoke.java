package com.weng.demo.proxy_model.dynamic_proxy.jdk;

import java.lang.reflect.InvocationTargetException;

/**
 * @DATE: 2022/9/8 10:10 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public interface DemoProxyHandleInvoke {
    Object invoke(Object service, Object[] args) throws InvocationTargetException, IllegalAccessException;
}

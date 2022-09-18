package com.weng.demo.proxy_model.dynamic_proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @DATE: 2022/9/8 9:41 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:代理拦截器
 */
public class DemoProxyHandler implements InvocationHandler {
    private List<DemoServerMethodInvoke> invokeList = DemoServerMethodInvokeHelp.getInstance().getInvokeList();
    private Object service;
    public DemoProxyHandler(Object service){
        this.service = service;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {

            DemoProxyHandleInvoke target = method::invoke;
            /**
             * 这个地方逻辑大概是这样
             * finalTarget = A;
             * target = B;
             * finalTarget.invoke() ---> 结束
             *
             * finalTarget = B;
             * target = C;
             * finalTarget.invoke() = A;
             *
             * 循环
             *
             */
            for (DemoServerMethodInvoke methodInvoke : invokeList) {
                DemoProxyHandleInvoke finalTarget = target;
                target = (Object service, Object[] objects)->{
                    //业务逻辑
                    return methodInvoke.intercept(service,method,objects,finalTarget);
                };

            }
            result = target.invoke(service,args);
            return result;
        }catch (Exception e){
            System.out.println(e);
        }



        return null;
    }
}

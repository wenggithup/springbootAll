package com.weng.demo.filter;

import com.weng.demo.util.MetricUtils;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @DATE: 2022/5/11 7:19 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Activate(group = CommonConstants.PROVIDER)
public class ServerFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ServerFilter.class);
    Map<String, Counter> counterMap = new ConcurrentHashMap<>();

    Map<String, Timer> timerMap = new ConcurrentHashMap<>();

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result = null;
        Class<?> serviceInterface = invoker.getInterface();
        Method method = null;
        Counter counter;
        Timer timer;
        try {

            method = invoker.getInterface().getMethod(invocation.getMethodName(), invocation.getParameterTypes());

            String keyMetric = serviceInterface.getSimpleName().concat("_").concat(serviceInterface.getSimpleName());
            if (null == counterMap.get(keyMetric)) {
                counter = MetricUtils.buildCounter
                        (serviceInterface.getSimpleName(), serviceInterface.getSimpleName() + "调用接口count", "methodName", method.getName());
            } else {
                counter = counterMap.get(keyMetric);
            }
            counter.increment();
            // 服务端接口日志
            long invokeBegin = System.currentTimeMillis();
            result = invoker.invoke(invocation);
            long invokeEnd = System.currentTimeMillis();
            long l = invokeEnd - invokeBegin;
            if (null == timerMap.get(keyMetric)) {
                timer = MetricUtils.buildTimer(
                        (serviceInterface.getSimpleName()), serviceInterface.getSimpleName() + "调用接口timer", "methodName", method.getName());
            } else {
                timer = timerMap.get(keyMetric);
            }
            timer.record(l, TimeUnit.MILLISECONDS);
            return result;
        } catch (Throwable invokeException) {
            logger.error("Server call service:{} method:{} error==>{}", serviceInterface.getSimpleName(), method.getName(), invokeException.getMessage(), invokeException);
            if (invokeException instanceof RpcException) {
                throw (RpcException) invokeException;
            }
            throw new RpcException(invokeException);
        }

    }
}

package com.vrv.im.filter;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import lombok.SneakyThrows;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.vrv.im.util.MetricUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static com.vrv.im.constant.MetricsConstant.*;


/**
 * @DATE: 2022/5/13 2:13 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Activate(group = CommonConstants.PROVIDER)
public class ServerFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(ServerFilter.class);
    private Map<String, Counter> counterMap = new ConcurrentHashMap<>();

    private Map<String, Timer> timerMap = new ConcurrentHashMap<>();



    @SneakyThrows
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        logger.info("into dubbo server invoke ......");
        Result result = null;
        Class<?> serviceInterface = invoker.getInterface();
        Method method = null;
        long invokeBegin = 0;
        String applicationName = null;
        String methodName = null;
        Counter counter = null;
        Timer timer = null;
        try {
            URL url = invoker.getUrl();
            applicationName = url.getApplication();
            method = invoker.getInterface().getMethod(invocation.getMethodName(), invocation.getParameterTypes());

            methodName = method.getName();

            invokeBegin = System.currentTimeMillis();
            result = invoker.invoke(invocation);

            counter = getMetric(Counter.class ,true, applicationName, methodName);
            if (null == counter){
                throw new RpcException("metric build counter error...");
            }
            counter.increment();

            long invokeEnd = System.currentTimeMillis();
            long l = invokeEnd - invokeBegin;
            timer = getMetric(Timer.class, true, applicationName, methodName);
            if (null == timer){
                throw new RpcException("metric build counter error...");
            }
            timer.record(l,TimeUnit.MILLISECONDS);
            return result;
        } catch (Throwable invokeException) {

            counter = getMetric(Counter.class, false, applicationName, methodName);
            if (null == counter){
                throw new RpcException("metric build counter error...");
            }
            counter.increment();

            long invokeEnd = System.currentTimeMillis();
            long l = invokeEnd - invokeBegin;
            timer = getMetric(Timer.class, false, applicationName, methodName);
            if (null == timer){
                throw new RpcException("metric build counter error...");
            }
            timer.record(l,TimeUnit.MILLISECONDS);

            logger.error("Server call service:{} method:{} error==>{}", serviceInterface.getSimpleName(), method.getName(), invokeException.getMessage(), invokeException);
            if (invokeException instanceof RpcException) {
                throw (RpcException) invokeException;
            }
            throw new RpcException(invokeException);
        }

    }

    private <T> T getMetric(Class<?> t,boolean status,String applicationName,String methodName) throws IllegalAccessException, InstantiationException {

        String keyMetric = status==true? applicationName.concat(".").concat(methodName).concat("success")
                :applicationName.concat(".").concat(methodName).concat("fail");

        if (t .equals(Counter.class)){
            Counter counter = counterMap.get(keyMetric);
            if (status) {
                if (null == counter) {
                    counter = MetricUtils.buildCounter
                            (METRICSPREFIX.concat(".counter"), applicationName + "调用各接口次数统计", APPLICATION_NAME_TAG,applicationName,
                                    METHOD_NAME_TAG, methodName, STATUS_TAG, "success");
                }
            }else {
                if (null == counter) {
                    counter = MetricUtils.buildCounter
                            (METRICSPREFIX.concat(".counter"), applicationName + "调用各接口次数统计", APPLICATION_NAME_TAG,
                                    applicationName,METHOD_NAME_TAG, methodName, STATUS_TAG, "fail");
                }
            }
            counterMap.put(keyMetric, counter);
            return (T)counter;
        }

        if (t .equals(Timer.class)){
            Timer timer = timerMap.get(keyMetric);
            if (status) {
                if (null == timer) {
                    timer = MetricUtils.buildTimer(
                            (METRICSPREFIX.concat(".timer")), applicationName + "调用各接口时间统计", APPLICATION_NAME_TAG,
                            applicationName,METHOD_NAME_TAG, methodName, STATUS_TAG, "success");
                }
            }else {
                if (null == timer) {
                    timer = MetricUtils.buildTimer
                            (METRICSPREFIX.concat(".timer"), applicationName + "调用各接口时间统计", APPLICATION_NAME_TAG,
                                    applicationName,METHOD_NAME_TAG, methodName, STATUS_TAG, "fail");
                }
            }
            timerMap.put(keyMetric, timer);
            return (T)timer;
        }
        return null;
    }
}

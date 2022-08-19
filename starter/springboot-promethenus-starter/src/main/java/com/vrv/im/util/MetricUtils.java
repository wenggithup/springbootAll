package com.vrv.im.util;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @DATE: 2022/5/12 4:58 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class MetricUtils {

    private  static MeterRegistry meterRegistry;

    private static final Logger logger = LoggerFactory.getLogger(MetricUtils.class);

    public static void buildMetric(MeterRegistry meterRegistry){
        MetricUtils.meterRegistry = meterRegistry;
    }

    public static Counter buildCounter(String name, String desc, String ...tags){
        logger.info("counter metrics build init...  name:{} ,desc :{} ,tags :{}",name,desc,tags);
        return Counter.builder(name)
                .tags(tags).description(desc).register(MetricUtils.meterRegistry);
    }

    public static Timer buildTimer(String name, String desc, String ...tags){
        logger.info("timer metrics build init...  name:{} ,desc :{} ,tags :{}",name,desc,tags);
        return Timer.builder(name)
                .tags(tags).description(desc).register(MetricUtils.meterRegistry);
    }
}

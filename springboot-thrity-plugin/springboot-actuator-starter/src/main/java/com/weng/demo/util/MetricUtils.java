package com.weng.demo.util;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

/**
 * @DATE: 2022/5/11 7:26 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class MetricUtils {
    private static MeterRegistry meterRegistry;


    public static void buildMetric(MeterRegistry meterRegistry) {
        MetricUtils.meterRegistry = meterRegistry;
    }

    public static Counter buildCounter(String name, String desc, String... tags) {
        return Counter.builder(name)
                .tags(tags).description(desc).register(meterRegistry);
    }

    public static Timer buildTimer(String name, String desc, String... tags) {
        return Timer.builder(name)
                .tags(tags).description(desc).register(meterRegistry);
    }
}

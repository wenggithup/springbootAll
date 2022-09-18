package com.weng.demo.config;

import com.weng.demo.VrvActuatorFactory;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import static com.weng.demo.util.MetricUtils.buildMetric;
import static com.weng.demo.util.ZookeeperUtils.buildCuratorFramework;

/**
 * @DATE: 2022/5/11 11:08 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Configuration
public class VrvActuatorConfiguration {
    private final ApplicationContext context;


    private final CuratorFramework curatorFramework;

    private final MeterRegistry meterRegistry;

    public VrvActuatorConfiguration(ApplicationContext context, CuratorFramework curatorFramework, MeterRegistry meterRegistry) {
        this.context = context;
        this.curatorFramework = curatorFramework;
        this.meterRegistry = meterRegistry;
        buildContext();
    }

    private void buildContext() {
        buildCuratorFramework(curatorFramework);
        buildMetric(meterRegistry);
        VrvActuatorFactory.prometheusBuild(context);

    }


}

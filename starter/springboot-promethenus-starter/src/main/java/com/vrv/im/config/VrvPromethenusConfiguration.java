package com.vrv.im.config;

import com.vrv.im.factory.VrvPromethenusFactory;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import static com.vrv.im.util.MetricUtils.buildMetric;
import static com.vrv.im.util.ZookeeperUtils.buildCuratorFramework;


/**
 * @DATE: 2022/5/12 4:34 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Configuration
@Import(ZookeeperConfiguration.class)
public class VrvPromethenusConfiguration {
    private final static Logger logger = LoggerFactory.getLogger(VrvPromethenusConfiguration.class);

    private final ApplicationContext context;


    private final CuratorFramework curatorFramework;

    private final MeterRegistry meterRegistry;
    


    public VrvPromethenusConfiguration(ApplicationContext context, CuratorFramework curatorFramework, MeterRegistry meterRegistry) {
        this.context = context;
        this.curatorFramework = curatorFramework;
        this.meterRegistry = meterRegistry;
        buildContext();
    }

    private void buildContext() {
        buildCuratorFramework(curatorFramework);
        buildMetric(meterRegistry);
        VrvPromethenusFactory.prometheusBuild(context);
    }
}

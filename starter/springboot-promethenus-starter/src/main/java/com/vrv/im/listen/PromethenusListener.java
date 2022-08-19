package com.vrv.im.listen;

import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.spring.context.event.DubboConfigInitEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.*;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @DATE: 2022/5/20 11:29 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class PromethenusListener implements ApplicationContextInitializer, ApplicationListener , Ordered {
    private final Logger logger = LoggerFactory.getLogger(PromethenusListener.class);

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        logger.info("PromethenusListener initialize start");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        logger.info("PromethenusListener onApplicationEvent event:" + event);

        if (event instanceof DubboConfigInitEvent){
            build(((DubboConfigInitEvent) event).getApplicationContext());
        }
    }

    private void build(ApplicationContext applicationContext) {
        ApplicationContext context = applicationContext;
        ConfigurableEnvironment env = (ConfigurableEnvironment) context.getEnvironment();

        MutablePropertySources propertySources = env.getPropertySources();
        Iterator<PropertySource<?>> iterator = propertySources.iterator();

        Map<String, Object> allSource = new HashMap(10);
        Map<String, Object> source = new HashMap(10);
        while (iterator.hasNext()) {
            PropertySource<?> propertySource = iterator.next();
            if (propertySource instanceof MapPropertySource) {
                for (String name : ((MapPropertySource) propertySource).getPropertyNames()) {
                    if (name.startsWith("management.server")) {
                        source.put(name.substring("management.server.".length()), propertySource.getProperty(name));
                    }
                    allSource.put(name, propertySource.getProperty(name));
                }
            }
        }

        logger.info("source :{}",JSON.toJSON(source));

        for (String name : source.keySet()) {
            Object value = source.get(name);
            if (value != null && value instanceof String) {
                String valueStr = (String) value;
                if (valueStr.startsWith("${") && valueStr.endsWith("}")) {
                    valueStr = valueStr.substring(2, valueStr.length() - 1);
                    value = allSource.get(valueStr);
                    if (value == null) {
                        value = System.getenv().get(valueStr);
                    }
                    source.put(name, value);
                }
            }
        }

    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 100;
    }
}

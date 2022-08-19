package com.vrv.im.listen;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @DATE: 2022/6/7 2:24 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class EnvironmentListener implements EnvironmentPostProcessor {
    private final Logger logger = LoggerFactory.getLogger(EnvironmentListener.class);

    private static final String PROPERTY_SOURCE_NAME = "defaultProperties";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources propertySources = environment.getPropertySources();
        for (PropertySource<?> propertySource : propertySources) {
            System.out.println("propertySources :" + JSON.toJSON(propertySource));
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("server.port", -1);
        AtomicBoolean flag = new AtomicBoolean(false);

        MapPropertySource target = null;
        Iterator<PropertySource<?>> iterator = propertySources.iterator();

/*        while (iterator.hasNext()) {
            PropertySource<?> propertySource = iterator.next();
            if (propertySource instanceof MapPropertySource) {
                logger.info("propertySource :{}", JSON.toJSON(propertySource));
                for (String name : ((MapPropertySource) propertySource).getPropertyNames()) {

                    if (name.equals("server.port")) {
                        Object property = propertySource.getProperty(name);
                        if (null == property || "".equals(property)) {
                            property = -1;
                            flag.set(true);
                        }
                    }
                }*//*

                 */

                if (target == null) {
                    target = new MapPropertySource(PROPERTY_SOURCE_NAME, map);
                }
                if (!propertySources.contains(PROPERTY_SOURCE_NAME)) {
                    propertySources.addFirst(target);
                }



    }
}




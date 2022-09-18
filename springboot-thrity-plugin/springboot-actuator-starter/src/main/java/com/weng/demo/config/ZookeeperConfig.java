package com.weng.demo.config;

import com.weng.demo.bean.RegistryProperties;
import com.weng.demo.bean.VrvActuatorProperties;
import com.weng.demo.bean.ZookeeperProperties;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @DATE: 2022/5/11 2:19 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Configuration
public class ZookeeperConfig {

    @Autowired
    private VrvActuatorProperties properties;


    @Bean(initMethod = "start", destroyMethod = "close")
    public CuratorFramework curatorFramework() {
        RegistryProperties registry = properties.getRegistry();
        ZookeeperProperties zookeeperProperties = registry.getZookeeper();
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry
                (zookeeperProperties.getBaseSleepTimeMs(), zookeeperProperties.getMaxRetries());
        return CuratorFrameworkFactory.builder()
                .connectString(zookeeperProperties.getAddress())
                .sessionTimeoutMs(zookeeperProperties.getSessionTimeout())
                .connectionTimeoutMs(zookeeperProperties.getConnectionTimeout())
                .retryPolicy(retry)
                .build();

    }

}

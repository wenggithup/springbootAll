package com.vrv.im.config;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.vrv.im.properties.PromethenusRegistryProperties;
import com.vrv.im.properties.VrvPromethenusProperties;
import com.vrv.im.properties.ZookeeperProperties;

/**
 * @DATE: 2022/5/12 4:35 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Configuration
public class ZookeeperConfiguration {

    @Autowired
    private VrvPromethenusProperties properties;




    @Bean(initMethod = "start",destroyMethod = "close")
    public CuratorFramework curatorFramework(){
        PromethenusRegistryProperties registry = properties.getRegistry();
        if (null == registry){
            throw new RuntimeException("properties did not configure \"zookeeper\" info");
        }
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

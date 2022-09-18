/*
package com.weng.demo.demo.kafka;

import com.vrv.im.config.FrameworkConfig;
import com.vrv.im.config.TomlConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZookeeperHelp {

    private static final Logger logger = LoggerFactory.getLogger(ZookeeperHelp.class);

    private static class ZkClientHolder {
        private static CuratorFramework client = connect();

        private static CuratorFramework connect() {
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5, 10000);
            // String zookeeperAddress = getBaseConfig("ZOOKEEPER_URL");
            String zookeeperAddress = FrameworkConfig.getZookeeperAddress();
            // zookeeperAddress="192.168.85.112:2181";
            // zookeeperAddress="123.59.28.80:2181";
            logger.info("ZookeeperHelp zookeeperAddress-----" + zookeeperAddress);
            if (StringUtils.isBlank(zookeeperAddress)) {
                logger.error("connect zookeeperAddress not found");

                return null;
            } else {
                client = CuratorFrameworkFactory.newClient(zookeeperAddress, 60 * 1000, 50 * 1000, retryPolicy);
                client.start();
                return client;
            }

        }
    }

    private ZookeeperHelp() {

    }

    public static CuratorFramework getConnect() {
        return ZkClientHolder.client;
    }

    public static void setConfig(Map<String, String> config) {
        if (!config.isEmpty()) {
            for (String key : config.keySet()) {
                if (StringUtils.isNotBlank(config.get(key))) {
                    try {
                        Stat stat = getConnect().checkExists().forPath(key);
                        if (stat == null) {
                            getConnect().create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(key,
                                config.get(key).getBytes("UTF-8"));
                        } else {
                            getConnect().setData().forPath(key, config.get(key).getBytes("UTF-8"));
                        }
                    } catch (Exception e) {
                        logger.error("setData error", e);
                    }
                }

            }
        }

    }

    public static String getData(String configName) throws Exception {
        try {

            CuratorFramework curatorFramework = getConnect();
            if (curatorFramework.checkExists().forPath(configName) != null) {
                byte[] bytes = curatorFramework.getData().forPath(configName);
                if (bytes.length > 0) {
                    return new String(bytes, "UTF-8");
                }
            }

        } catch (Exception e) {
            logger.error("getData error", e);
            throw e;
        }
        return null;
    }

    public static List<String> getChildren(String path) {
        try {

            return getConnect().getChildren().forPath(path);
        } catch (Exception e) {
            logger.error("getChildren error", e);
        }
        return new ArrayList<>();
    }

    public static void delNode(String path) throws Exception {
        try {
            getConnect().delete().forPath(path);
        } catch (Exception e) {
            logger.error("delNode error", e);
        }
    }

    // 读取环境变量
    public static String getBaseConfig(String key) {
        return System.getenv(key);
    }

    */
/**
 * 获取zookeeper namespace
 *
 * @author chenlong
 * @date 2021-06-09 17:20:57
 * @return
 *//*

    public static String getNamespace() {
        TomlConfig tomlConfig = TomlConfig.getInstance();
        return tomlConfig.getZookeeperNamespace();
    }

}
*/

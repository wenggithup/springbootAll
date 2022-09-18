package com.weng.demo.util;

import com.weng.demo.VrvActuatorFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @DATE: 2022/5/11 4:37 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class ZookeeperUtils {
    private static CuratorFramework client;
    private final static Logger logger = LoggerFactory.getLogger(ZookeeperUtils.class);

    public static void buildCuratorFramework(CuratorFramework curatorFramework) {
        ZookeeperUtils.client = curatorFramework;
    }


    public static String createEphemeralNode(String path, String nodeValue) {
        try {
            if (!checkExists(path)) {
                return client.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)
                        .forPath(path, nodeValue.getBytes());
            } else {
                throw new RuntimeException("zk node is exist....");
            }
        } catch (Exception e) {
            logger.error("create node error...,path:{},nodeValue:{}", path, nodeValue, e);
        }
        return null;
    }


    public static String getData(String path) throws Exception {
        try {
            if (checkExists(path)) {
                byte[] bytes = client.getData().forPath(path);
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


    public static boolean checkExists(String path) {
        try {
            Stat stat = client.checkExists().forPath(path);

            return stat != null;
        } catch (Exception e) {
            logger.error("check zk node error...,path:{}", path, e);
        }
        return false;
    }


}

package com.weng.business.organization.config;

import com.ibm.etcd.client.EtcdClient;
import com.ibm.etcd.client.kv.KvClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @DATE: 2022/1/25 5:04 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class Test {
    private static Logger logger = LoggerFactory.getLogger(Test.class);

    private static EtcdClient client;

    static {
        getClientConnect();
    }

    private static void getClientConnect() {
        logger.debug("init etcd client start...");
        client=EtcdClient.forEndpoint("127.0.0.1",2379).build();
        logger.debug("init etcd client end...");
    }


    public static KvClient getKvClient() {
        return client.getKvClient();
    }


}

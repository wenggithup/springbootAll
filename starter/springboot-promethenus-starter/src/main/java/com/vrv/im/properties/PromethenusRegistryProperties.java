package com.vrv.im.properties;

import lombok.Data;

/**
 * @DATE: 2022/5/12 4:31 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Data
public class PromethenusRegistryProperties {
    /**
     * 注册模式
     */
    private String model;

    /**
     * zk信息
     */
    private ZookeeperProperties zookeeper;
}

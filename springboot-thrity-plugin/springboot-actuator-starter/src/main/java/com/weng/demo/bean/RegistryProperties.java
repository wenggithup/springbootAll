package com.weng.demo.bean;

import lombok.Data;

/**
 * @DATE: 2022/5/10 5:48 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Data
public class RegistryProperties {
    //注册模式默认zookeeper
    private String model;

    private ZookeeperProperties zookeeper;

}

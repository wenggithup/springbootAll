package com.weng.demo.bean;

import lombok.Data;

/**
 * @DATE: 2022/5/10 5:49 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Data
public class ZookeeperProperties {
    //优先级 address> addressEnv
    private String address;

    private String path;

    //会话超时时间
    private int sessionTimeout = 100*1000;

    //链接超时
    private int connectionTimeout = 50*1000;

    //重试间隔
    private int baseSleepTimeMs = 3*1000;

    //最大重试次数
    private int maxRetries = 5;

}

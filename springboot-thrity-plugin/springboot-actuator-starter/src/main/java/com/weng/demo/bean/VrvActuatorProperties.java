package com.weng.demo.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @DATE: 2022/5/10 5:45 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@ConfigurationProperties(prefix = "vrv.actuator")
@Data
public class VrvActuatorProperties {
    private EndpointsProperties endpoint = new EndpointsProperties();

    private RegistryProperties registry;

    private String protocol;

}

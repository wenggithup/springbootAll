package com.vrv.im.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @DATE: 2022/5/12 4:23 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description: 普罗米修斯配置类
 */

@Data
@ConfigurationProperties(prefix = "vrv.promethenus")
public class VrvPromethenusProperties {

    private PromethenusRegistryProperties registry;

    private String protocol;
}

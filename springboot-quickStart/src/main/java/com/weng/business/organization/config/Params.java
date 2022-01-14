package com.weng.business.organization.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @DATE: 2022/1/14 6:13 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Component
@ConfigurationProperties("params")
@Data
public class Params {
    public String trustStorePath="/config/truststore.jks";
    public String trustStorePassword="vrv123456";
    public String keyStorePath = "/config/keystore.jks";
    public String keyStorePassword = "vrv123456";
    public String defaultType = "JKS";

}

package com.vrv.im.properties;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @DATE: 2022/5/12 4:30 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description: 端点配置，默认统一管理配置
 */
@Data
public class PromethenusEndpointsProperties {

    private String basePath = "/vrvActuator" ;

    private String baseExposePath = "/prometheus";

    private Set<String> exposeInclude = new HashSet<>();

    private Set<String> exposeExclude = new HashSet<>();
}

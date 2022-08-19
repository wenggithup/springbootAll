package com.weng.demo.bean;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @DATE: 2022/5/11 10:57 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Data
public class EndpointsProperties {
    private String basePath = "/vrvActuator" ;

    private String baseExposePath = "/prometheus";

    private Set<String> exposeInclude = new HashSet<>();

    private Set<String> exposeExclude = new HashSet<>();

}

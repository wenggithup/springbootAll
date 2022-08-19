package com.weng.demo;

import com.weng.demo.bean.VrvActuatorProperties;
import com.weng.demo.config.VrvActuatorConfiguration;
import com.weng.demo.config.ZookeeperConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @DATE: 2022/5/10 5:13 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Configuration
@Import({VrvActuatorProperties.class, VrvActuatorConfiguration.class})
public class ActuatorAutoConfig {
}

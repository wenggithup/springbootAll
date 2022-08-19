package com.vrv.im;

import com.vrv.im.config.VrvPromethenusConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.vrv.im.properties.VrvPromethenusProperties;

/**
 * @DATE: 2022/5/12 4:20 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Configuration
@Import({VrvPromethenusProperties.class, VrvPromethenusConfiguration.class})
public class VrvPromethenusAutoConfig {
}

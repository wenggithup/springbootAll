package com.weng.demo.datasource.annotation;

import java.lang.annotation.*;

/**
 * @DATE: 2022/9/14 3:30 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Target({ ElementType.METHOD,ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DynamicSource {

/**
 * datasourceId，必填选项
 * @return
 */
  String value();

}

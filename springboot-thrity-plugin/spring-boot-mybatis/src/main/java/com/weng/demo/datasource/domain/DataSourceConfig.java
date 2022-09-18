package com.weng.demo.datasource.domain;

import com.weng.demo.datasource.annotation.DynamicSource;
import com.weng.demo.datasource.constant.DataSourceConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @DATE: 2022/9/14 2:10 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSourceConfig {

    private DataSourceConstant dataSourceConstant;
}

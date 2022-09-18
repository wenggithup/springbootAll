package com.weng.demo.datasource.constant;

import lombok.Data;

/**
 * @DATE: 2022/9/14 2:12 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */

public enum DataSourceConstant {
    /**
     * 德鲁伊
     */
    DURID_DATASOURCE("durid",1),

    /**
     * hikari
     */
    HIKARI_DATASOURCE("hikari",2);

    ;

    DataSourceConstant(String dataSourceFactoryName, Integer dataSourceId) {
        this.dataSourceFactoryName = dataSourceFactoryName;
        this.dataSourceId = dataSourceId;
    }

    private String dataSourceFactoryName;

    private Integer dataSourceId;
}

package com.weng.demo.datasource.util;

import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @DATE: 2022/9/14 2:53 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class DynamicDataSourceHelp {

    /**
     * 每一个k 对应一个数据源
     */
    private  ConcurrentMap<String, DataSource> dataSourceMap = new ConcurrentHashMap<>(10);

    public  ConcurrentMap<String,DataSource> getDataSourceMap(){
        return dataSourceMap;
    }

    public  void setDataSourceMap(String dataSourceId,DataSource dataSource){
        dataSourceMap.putIfAbsent(dataSourceId,dataSource);
    }

    private DynamicDataSourceHelp(){

    }

    private static DynamicDataSourceHelp dynamicDataSourceHelp;

    public static DynamicDataSourceHelp getInstance(){
        if (null == dynamicDataSourceHelp){
            synchronized (DynamicDataSourceHelp.class){
                if (null == dynamicDataSourceHelp){
                    dynamicDataSourceHelp = new DynamicDataSourceHelp();
                }
            }
        }
        return dynamicDataSourceHelp;
    }
}

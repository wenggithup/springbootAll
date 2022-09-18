package com.weng.demo.datasource.factory;

import ch.qos.logback.core.util.StringCollectionUtil;
import com.weng.demo.datasource.constant.DataSourceConstant;
import com.weng.demo.datasource.domain.DataSourceConfig;
import com.weng.demo.datasource.domain.DataSourceProperties;
import com.weng.demo.datasource.util.DynamicDataSourceHelp;
import org.springframework.jdbc.datasource.AbstractDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @DATE: 2022/9/14 2:46 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:动态数据实现
 */
public class DynamicDataSource extends AbstractDataSource {
    private DataSourceConfig dataSourceConfig;
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public DynamicDataSource(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
        loadDataSource();
    }

    public static void setThreadLocal(String dataSourceId) {
        threadLocal.set(dataSourceId);
    }

    /**
     * 加载数据源，这里多数据源的情况下，多数据源如何取是个问题？
     */
    private void loadDataSource() {
        //例如，从中间价中获取/自定义配置文件取，这里先忽略。主要是演示思路
        //TODO 获取多数据源配置
        Map<String,DataSourceProperties> map = new HashMap<>();
        //判断使用哪个连接池创建

        DataSourceConstant constant = dataSourceConfig.getDataSourceConstant();
        map.forEach((dataSourceId,properties)->{

            DataSource dataSource = null;
            switch (constant){
                case DURID_DATASOURCE:
                    dataSource = new DruidDataSourceFactory(properties);
                    break;
                case HIKARI_DATASOURCE:
                default:
                    dataSource = new HikariDataSourceFactory(properties);
            }

            DynamicDataSourceHelp.getInstance().setDataSourceMap(dataSourceId,dataSource);
        });

    }

    @Override
    public Connection getConnection() throws SQLException {
        return getConnect().getConnection();
    }

    private DataSource getConnect() {
       //这里会在拦截器中获取key的值。
        String dataSourceId =threadLocal.get();
        if (null == dataSourceId || "".equals(dataSourceId)){
            throw new RuntimeException("dataSourceId is null");
        }
        removeThreadLocal();
        return DynamicDataSourceHelp.getInstance().getDataSourceMap().get(dataSourceId);


    }

    private void removeThreadLocal(){
        threadLocal.remove();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getConnect().getConnection(username,password);
    }


    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return getConnect().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return getConnect().isWrapperFor(iface);
    }
}

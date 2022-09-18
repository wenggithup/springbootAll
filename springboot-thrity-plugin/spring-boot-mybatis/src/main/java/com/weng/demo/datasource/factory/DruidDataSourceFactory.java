package com.weng.demo.datasource.factory;

import com.alibaba.druid.pool.DruidDataSource;
import com.weng.demo.datasource.domain.DataSourceProperties;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @DATE: 2022/9/14 10:45 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class DruidDataSourceFactory extends AbstractDatasourceFactory {
    private DataSourceProperties dataSourceProperties;

    public DruidDataSourceFactory(DataSourceProperties dataSourceProperties) {
        super(dataSourceProperties);
        this.dataSourceProperties = dataSourceProperties;
    }

    @Override
    DataSource getDataSource() {
        // 用户名 TODO 加解密
        String user = dataSourceProperties.getUser();
        // 解密
        String pwd = dataSourceProperties.getPwd();

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriverClass());
        dataSource.setUrl(dataSourceProperties.getJdbcUrl());
        dataSource.setUsername(user);
        dataSource.setPassword(pwd);
        // initialSize
        dataSource.setInitialSize(10);
        // minIdle
        dataSource.setMinIdle(10);
        // maxActive
        dataSource.setMaxActive(60);
        // maxWait
        dataSource.setMaxWait(5000);
        // poolPreparedStatements
        dataSource.setPoolPreparedStatements(true);
        // maxPoolPreparedStatementPerConnectionSize
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        // timeBetweenEvictionRunsMillis
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        // minEvictableIdleTimeMillis
        dataSource.setMinEvictableIdleTimeMillis(300000);
        // testWhileIdle
        dataSource.setTestWhileIdle(true);
        // testOnBorrow
        dataSource.setTestOnBorrow(false);
        // testOnReturn
        dataSource.setTestOnReturn(false);
        // validationQuery
        dataSource.setValidationQuery("select 1");
        dataSource.setKeepAlive(true);
        dataSource.setAsyncInit(false);
        try {
            dataSource.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //其他的参数可以默认加上去
        return dataSource;
    }

    @Override
    void closeDataSource() {

    }
}

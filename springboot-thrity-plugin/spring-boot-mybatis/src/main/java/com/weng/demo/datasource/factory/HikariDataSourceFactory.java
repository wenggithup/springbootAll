package com.weng.demo.datasource.factory;

import com.weng.demo.datasource.domain.DataSourceProperties;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @DATE: 2022/9/14 10:45 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class HikariDataSourceFactory extends AbstractDatasourceFactory {
    private DataSourceProperties dataSourceProperties;

    public HikariDataSourceFactory(DataSourceProperties dataSourceProperties) {
        super(dataSourceProperties);
        this.dataSourceProperties = dataSourceProperties;
    }

    @Override
    DataSource getDataSource() {
        // 用户名 TODO 加解密
        String user = dataSourceProperties.getUser();
        // 解密
        String pwd = dataSourceProperties.getPwd();

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriverClass());
        dataSource.setJdbcUrl(dataSourceProperties.getJdbcUrl());
        dataSource.setUsername(user);
        dataSource.setPassword(pwd);
        // initialSize
        dataSource.setPoolName("Hikari-DataSource");
        // minIdle
        dataSource.setMinimumIdle(10);
        // maxActive
        dataSource.setMaximumPoolSize(60);
        // maxWait
        dataSource.setIdleTimeout(180000);
        // poolPreparedStatements
        dataSource.setConnectionInitSql("SELECT 1");
        // maxPoolPreparedStatementPerConnectionSize
        dataSource.setConnectionTimeout(30000);

        try {
            dataSource.getConnection();
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

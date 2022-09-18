package com.weng.demo.datasource.factory;

import com.weng.demo.datasource.domain.DataSourceProperties;
import org.springframework.jdbc.datasource.AbstractDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @DATE: 2022/9/14 9:55 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public abstract class AbstractDatasourceFactory extends AbstractDataSource {

    public AbstractDatasourceFactory(DataSourceProperties dataSourceProperties) {

    }


    /**
     * 这里考虑如何取数据源的值，有几个方案，一个是自定义配置properties，然后读取其中的值。或者读取spring.datasource.xx的值，并将此数据源设置为主数据源
     * 第二种是从中间件里面取
     */


    @Override
    public Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getDataSource().getConnection(username, password);
    }


    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return getDataSource().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return getDataSource().isWrapperFor(iface);
    }

    /**
     * 获取数据源
     *
     * @return
     */
    abstract DataSource getDataSource();

    /**
     * 关闭数据源
     */
    abstract void closeDataSource();
}

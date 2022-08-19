package com.weng.demo.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 * @DATE: 2021/12/6 5:28 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description: 数据源工具类
 */
public class DataSourceUtil {

    private static final String HOST = "localhost";

    private static final int PORT = 13306;

    private static final String USER_NAME = "root";

    private static final String PASSWORD = "123456";

    private static final long KEEPALIVE = 30l;

    public static DataSource createDataSource(final String dataSourceName) {
        DruidDataSource result = new DruidDataSource();
        result.setDriverClassName("com.mysql.jdbc.Driver");
        result.setUrl(String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8", HOST, PORT, dataSourceName));
        result.setUsername(USER_NAME);
        result.setPassword(PASSWORD);
        result.setKeepAliveBetweenTimeMillis(KEEPALIVE);
        result.setKeepAlive(true);
        return result;
    }

}

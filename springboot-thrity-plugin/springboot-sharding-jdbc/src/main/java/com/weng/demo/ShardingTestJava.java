package com.weng.demo;

import com.weng.demo.conf.ShardingDatabasesAndTableConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @DATE: 2021/12/7 1:50 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class ShardingTestJava {
    public static void main(String[] args) throws SQLException {
        DataSource dataSource = ShardingDatabasesAndTableConfiguration.getDataSource();

        try {
            Connection connection = dataSource.getConnection();
            //for (int i = 0; i < 10; i++) {


            String sql = "INSERT INTO t_order (user_id, address_id, status) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, 122);
            preparedStatement.setLong(2, 456);
            preparedStatement.setString(3, "order.getStatus()");
            preparedStatement.executeUpdate();
            // }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
        }

    }

}

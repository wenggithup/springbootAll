package com.weng.business.organization.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * @DATE: 2022/7/6 4:01 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Component
public class HikariLoader implements ApplicationRunner {
    private final HikariDataSource hikariDataSource;

    @Autowired
    public HikariLoader(HikariDataSource hikariDataSource) {
        this.hikariDataSource = hikariDataSource;
    }

    @Override
    @Autowired
    public void run(ApplicationArguments args) throws SQLException {
        hikariDataSource.getConnection();
    }


}

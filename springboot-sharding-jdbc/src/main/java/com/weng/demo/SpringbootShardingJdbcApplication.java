package com.weng.demo;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
@MapperScan("com.weng.demo.*.mapper")
public class SpringbootShardingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShardingJdbcApplication.class, args);
    }

}

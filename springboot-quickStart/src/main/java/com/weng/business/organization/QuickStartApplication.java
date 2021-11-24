package com.weng.business.organization;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @DATE: 2021/11/21 5:54 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@SpringBootApplication
@MapperScan("com.weng.business.*.mapper")
public class QuickStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickStartApplication.class, args);
    }

}

package com.weng.demo;

import com.weng.demo.config.ZipKinClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @DATE: 2022/1/11 10:25 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@SpringBootApplication
@Import(ZipKinClientConfiguration.class)
public class ZipkinBootSever3Application {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinBootSever3Application.class,args);
    }
}

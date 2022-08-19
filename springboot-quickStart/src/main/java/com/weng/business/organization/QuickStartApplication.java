package com.weng.business.organization;

import com.weng.business.organization.config.Params;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
//        String tsp = "/Users/wengchuanjie/Develop/project/Only-weng/springBootProject/spring-boot-demo-parent/springboot-quickStart/config/truststore.jks";
//        System.setProperty("javax.net.ssl.trustStore", tsp);
//        System.setProperty("javax.net.ssl.trustStorePassword", "vrv123456");
//
//        String ksp = "/Users/wengchuanjie/Develop/project/Only-weng/springBootProject/spring-boot-demo-parent/springboot-quickStart/config/keystore.jks";
//        System.setProperty("javax.net.ssl.keyStore",ksp);
//        System.setProperty("javax.net.ssl.keyStorePassword","vrv123456");
        SpringApplication.run(QuickStartApplication.class, args);
        String start = args[0];
        String end = args[1];
        int startIndex = 0;
        int endIndex = 0;
        // 获取main方法中两个参数在版本信息集合中的索引位置
        // 判断传过来的两个参数值,然后做处理
        if (version.contains(start) && version.contains(end)) {
            startIndex = version.indexOf(start);
            endIndex = version.indexOf(end);
        }

        for (int i = startIndex + 1; i <= endIndex; i++) {
            System.out.println(dataMove.get(i));
        }
    }
    public static List<String> version = new ArrayList<>();
    public static List<String> dataMove = new ArrayList<>();
    //初始化添加版本信息
    static {
        version.add("first");
        version.add("account");
        version.add("none");
        version.add("configCenter");
        version.add("V1.3.7");
        version.add("V1.3.12");
        version.add("V1.4.1");
        version.add("V1.5.1");
        version.add("V1.6.1");
        version.add("V1.7.2");
        version.add("V1.8.2");
        version.add("V1.9.3");
        version.add("V1.10.1");
        version.add("V1.11.1");
        version.add("V1.12.1");
        version.add("V2.0.1");
        version.add("V3.5.1");
        version.add("V3.6.1");
        version.add("V4.1.1");
        version.add("V4.1.2");
        version.add("V4.1.3");
        version.add("V4.4.0");
        version.add("V4.6.0");
        version.add("V4.7.0");
        version.add("V4.9.0");
        version.add("V4.11.0");
        version.add("V4.12.0");
        version.add("V5.4.0");
        version.add("V5.5.0");
        version.add("V5.6.0");
        version.add("V5.7.0");
        version.add("V5.8.0");
        version.add("V5.9.0");
        version.add("V5.10.0");
        version.add("V5.12.0");
        version.add("V5.13.0");
        version.add("V5.15.0");
        version.add("V5.16.0");
        version.add("V5.17.0");
        version.add("V6.0.1.1");
        version.add("V6.0.1.6");
        version.add("V6.0.2.0");
        version.add("VUserBase");
        dataMove.add("first");
        dataMove.add("account");
        dataMove.add("none");
        dataMove.add("configCenter");
        dataMove.add("V1.3.7");
        dataMove.add("V1.3.12");
        dataMove.add("V1.4.1");
        dataMove.add("V1.5.1");
        dataMove.add("V1.6.1");
        dataMove.add("V1.7.2");
        dataMove.add("V1.8.2");
        dataMove.add("V1.9.3");
        dataMove.add("V1.10.1");
        dataMove.add("V1.11.1");
        dataMove.add("V1.12.1");
        dataMove.add("V2.0.1");
        dataMove.add("V3.5.1");
        dataMove.add("V3.6.1");
        dataMove.add("V4.1.1");
        dataMove.add("V4.1.2");
        dataMove.add("V4.1.3");
        dataMove.add("V4.4.0");
        dataMove.add("V4.6.0");
        dataMove.add("V4.7.0");
        dataMove.add("V4.9.0");
        dataMove.add("V4.11.0");
        dataMove.add("V4.12.0");
        dataMove.add("V5.4.0");
        dataMove.add("V5.5.0");
        dataMove.add("V5.6.0");
        dataMove.add("V5.7.0");
        dataMove.add("V5.8.0");
        dataMove.add("V5.9.0");
        dataMove.add("V5.10.0");
        dataMove.add("V5.12.0");
        dataMove.add("V5.13.0");
        dataMove.add("V5.15.0");
        dataMove.add("V5.16.0");
        dataMove.add("V5.17.0");
        dataMove.add("V6.0.1.1");
        dataMove.add("V6.0.1.6");
        dataMove.add("V6.0.2.0");
        dataMove.add("VUserBase");
    }

    @PostConstruct
    void postConstruct(){


    }
}



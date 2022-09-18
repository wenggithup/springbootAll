package com.weng.business.organization;

import com.weng.business.organization.config.CustomBeanNameGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @DATE: 2021/11/21 5:54 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@SpringBootApplication
@MapperScan("com.weng.business.organization.mapper")
public class QuickStartApplication {

    public static void main(String[] args) {
//        String tsp = "/Users/wengchuanjie/Develop/project/Only-weng/springBootProject/spring-boot-demo-parent/springboot-quickStart/config/truststore.jks";
//        System.setProperty("javax.net.ssl.trustStore", tsp);
//        System.setProperty("javax.net.ssl.trustStorePassword", "vrv123456");
//
//        String ksp = "/Users/wengchuanjie/Develop/project/Only-weng/springBootProject/spring-boot-demo-parent/springboot-quickStart/config/keystore.jks";
//        System.setProperty("javax.net.ssl.keyStore",ksp);
//        System.setProperty("javax.net.ssl.keyStorePassword","vrv123456");
//        SpringApplication.run(QuickStartApplication.class, args);
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(QuickStartApplication.class)

                .beanNameGenerator((a, b) -> a.getBeanClassName())

                .run(args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        System.out.println(environment.getProperty("spring.application.name"));
        System.out.println(environment.getProperty("spring.application.version"));
    }
}



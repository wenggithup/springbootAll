package com.weng.business.organization;

import com.weng.business.organization.config.Params;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.File;

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

/*    @Autowired
    Params params;
    @PostConstruct
    void postConstruct(){
        File trustStoreFilePath = new File(params.trustStorePath);
        String tsp = "/Users/wengchuanjie/Develop/project/Only-weng/springBootProject/spring-boot-demo-parent/springboot-quickStart/config/truststore.jks";
        System.setProperty("javax.net.ssl.trustStore", tsp);
        System.setProperty("javax.net.ssl.trustStorePassword", params.trustStorePassword);
        System.setProperty("javax.net.ssl.keyStoreType", params.defaultType);
        File keyStoreFilePath = new File(params.keyStorePath);
        String ksp = "/Users/wengchuanjie/Develop/project/Only-weng/springBootProject/spring-boot-demo-parent/springboot-quickStart/config/keystore.jks.jks;";
        System.setProperty("Security.KeyStore.Location", ksp);
        System.setProperty("Security.KeyStore.Password", params.keyStorePassword);

        //-Djavax.net.ssl.trustStore="/Users/wengchuanjie/Develop/project/Only-weng/springBootProject/spring-boot-demo-parent/springboot-quickStart/config/truststore.jks"
        //-Djavax.net.ssl.trustStorePassword="vrv123456" -Djavax.net.ssl.keyStoreType="JKS"
        //-DSecurity.KeyStore.Location="/Users/wengchuanjie/Develop/project/Only-weng/springBootProject/spring-boot-demo-parent/springboot-quickStart/config/keystore.jks.jks"
        //-DSecurity.KeyStore.Password="vrv123456"
    }*/
}



package com.weng.business.organization.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;

/**
 * @DATE: 2022/9/1 2:43 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
//@Configuration
public class SqlSessionFactoryConfig {
    @Resource
    HikariDataSource dataSource;

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);//更多参数请自行注入
        bean.setPlugins(new MybatisInterceptor());
        org.springframework.core.io.Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/organization/*.xml");
        bean.setMapperLocations(resources);
        return bean.getObject();
    }
}

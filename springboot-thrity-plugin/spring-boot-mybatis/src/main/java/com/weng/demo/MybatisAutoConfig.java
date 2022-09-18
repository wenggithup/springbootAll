package com.weng.demo;

import com.weng.demo.datasource.aop.DynamicAdvice;
import com.weng.demo.datasource.constant.DataSourceConstant;
import com.weng.demo.datasource.domain.DataSourceConfig;
import com.weng.demo.datasource.domain.DataSourceProperties;
import com.weng.demo.datasource.factory.DruidDataSourceFactory;
import com.weng.demo.datasource.factory.DynamicDataSource;
import com.weng.demo.datasource.factory.HikariDataSourceFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @DATE: 2022/9/14 9:52 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Import({DataSourceProperties.class, DynamicAdvice.class})
public class MybatisAutoConfig {

    @Bean
    @ConditionalOnMissingBean(DataSourceConfig.class)
    public DataSourceConfig dataSourceConfig(){
        return new DataSourceConfig(DataSourceConstant.HIKARI_DATASOURCE);
    }


    /**
     * 这里先实现单数据源
     *
     * @return
     */
    @Bean
    @Primary
    public DataSource dataSource(DataSourceProperties properties,DataSourceConfig dataSourceConfig) {
        DataSourceConstant constant = dataSourceConfig.getDataSourceConstant();
        switch (constant){
            case DURID_DATASOURCE:
                return new DruidDataSourceFactory(properties);
            case HIKARI_DATASOURCE:
            default:
                return new HikariDataSourceFactory(properties);
        }

    }

    /**
     * 多数据源
     */
    public DataSource dataSource(DataSourceConfig dataSourceConfig){
        return new DynamicDataSource(dataSourceConfig);
    }


}

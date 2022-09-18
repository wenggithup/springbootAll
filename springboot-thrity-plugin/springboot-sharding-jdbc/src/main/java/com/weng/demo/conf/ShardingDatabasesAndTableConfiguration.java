package com.weng.demo.conf;

import com.weng.demo.util.DataSourceUtil;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.keygen.KeyGenerateStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @DATE: 2021/12/6 5:30 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description: 分片规则配置类
 */
public class ShardingDatabasesAndTableConfiguration {
    private static Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("ds0", DataSourceUtil.createDataSource("shard01"));
        dataSourceMap.put("ds1", DataSourceUtil.createDataSource("shard02"));
        return dataSourceMap;
    }

    private static ShardingRuleConfiguration createShardingRuleConfiguration() {
        ShardingRuleConfiguration configuration = new ShardingRuleConfiguration();
        //设置表的分片规则
        configuration.getTables().add(getOrderTableRuleConfiguration());
//        configuration.getBindingTableGroups().add("t_order,t_order_item");
        //
        //
        /**
         * 设置数据库的分片规则
         * inline表示行表达式分片算法，它使用groovy的表达式，支持单分片键，比如 t_user_$->{uid%8} 表示t_user表根据u_id%8分成8张表
         */
        configuration.setDefaultDatabaseShardingStrategy(
                new StandardShardingStrategyConfiguration("user_id", "inline"));
        /**
         * 设置表的分片规则
         */
        configuration.setDefaultTableShardingStrategy(new StandardShardingStrategyConfiguration("order_id", "order_inline"));
        Properties props = new Properties();
        props.setProperty("algorithm-expression", "ds${user_id%2}"); //表示根据user_id取模得到目标数据库
        /**
         * 定义具体的分片规则算法，用于提供分库分表的算法规则
         */
        configuration.getShardingAlgorithms().put("inline", new ShardingSphereAlgorithmConfiguration("INLINE", props));
        Properties properties = new Properties();
        properties.setProperty("algorithm-expression", "t_order_${order_id%2}");
        configuration.getShardingAlgorithms().put("order_inline", new ShardingSphereAlgorithmConfiguration("INLINE", properties));
        configuration.getKeyGenerators().put("snowflake", new ShardingSphereAlgorithmConfiguration("SNOWFLAKE", getProperties()));
        return configuration;
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("worker-id", "123");
        return properties;
    }

    //创建订单表的分片规则,创建逻辑表和真实表
    private static ShardingTableRuleConfiguration getOrderTableRuleConfiguration() {
        /**
         * logicTable String  	分片逻辑表名称
         * actualDataNodes String 由数据源名 + 表名组成，以小数点分隔。多个表以逗号分隔，支持行表达式,默认值 使用已知数据源与逻辑表名称生成数据节点，用于广播表或只分库不分表且所有库的表结构完全一致的情况
         * databaseShardingStrategy ShardingStrategyConfiguration 分库策略；	使用默认分库策略
         * tableShardingStrategy；ShardingStrategyConfiguration；分表策略；使用默认分表策略
         * keyGenerateStrategy；KeyGeneratorConfiguration；自增列生成器；使用默认自增主键生成器
         *
         */
        ShardingTableRuleConfiguration tableRule = new ShardingTableRuleConfiguration("t_order", "ds${0..1}.t_order_${0..1}");
        //使用雪花算法作为主键策略
        tableRule.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("order_id", "snowflake"));
        return tableRule;
    }

    public static DataSource getDataSource() throws SQLException {
        return ShardingSphereDataSourceFactory.createDataSource(createDataSourceMap(), Collections.singleton(createShardingRuleConfiguration()), new Properties());
    }

}

package com.weng.demo.springbootelasticsearch.config;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.PutIndexTemplateRequest;
import org.elasticsearch.common.settings.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

/**
 * @DATE: 2022/2/15 8:03 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Configuration
public class ElasticSearchConfig {
    private final static Logger logger = LoggerFactory.getLogger(ElasticSearchConfig.class);
    @Autowired
    public RestHighLevelClient client;


    private final String template_prefix = "weng_index_name";

    /**
     * 创建索引模板
     * @throws IOException
     */
    @PostConstruct
    public void initMapping() throws IOException {
    logger.info("init create mapping。。。。。");
    //模板名称
    PutIndexTemplateRequest pri = new PutIndexTemplateRequest("weng_template");
    //所有以 weng_index_name 都将匹配template_weng这个模板
    pri.patterns(Collections.singletonList("*" + template_prefix));
    //设置项完善
    pri.settings(Settings.builder()
            //设置五个主分片一个复制分片
            .put("index.number_of_replicas", 1)
            .put("index.number_of_shards", 5));

    Map<String, Object> jsonMap = new HashMap<>();
    //关闭数字检测，这里不需要数字检测
    jsonMap.put("numeric_detection", false);
    //设置动态模板
    List<Map<String, Object>> dynamic_templates = new ArrayList<>();
    jsonMap.put("dynamic_templates", dynamic_templates);
    {
            Map<String, Object> elment = new HashMap<>();
            dynamic_templates.add(elment);
            Map<String, Object> es = new HashMap<>();
            //针对text类型的匹配，标准分词
            elment.put("standard-text", es);
            Map<String, Object> mapping = new HashMap<>();
            es.put("mapping", mapping);
            //搜索分词器
            mapping.put("search_analyzer", "standard");
            mapping.put("analyzer", "standard");//ik_smart
            mapping.put("type", "text");
            //匹配描述字段description
            es.put("match", "description");
    }
    {
        Map<String, Object> elment = new HashMap<>();
        dynamic_templates.add(elment);
        Map<String, Object> es = new HashMap<>();
        elment.put("match_string", es);
        Map<String, Object> mapping = new HashMap<>();
        es.put("mapping", mapping);
        mapping.put("analyzer", "keyword");
        mapping.put("fielddata", true);//ik_smart
        es.put("match", "*");
        es.put("match_mapping_type", "string");
    }
        pri.mapping(jsonMap);
        client.indices().putTemplate(pri, RequestOptions.DEFAULT).isAcknowledged();
        logger.info("create mapping finish");
    }


/*    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.6.84", 21110, "http")));
        return client;
    }*/


}

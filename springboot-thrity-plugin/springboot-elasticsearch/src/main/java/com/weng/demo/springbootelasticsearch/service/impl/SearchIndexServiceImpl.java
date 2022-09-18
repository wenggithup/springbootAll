package com.weng.demo.springbootelasticsearch.service.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.weng.demo.springbootelasticsearch.dto.SearchDocReq;
import com.weng.demo.springbootelasticsearch.service.SearchIndexService;
import com.xiaoleilu.hutool.util.CollectionUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @DATE: 2022/3/11 11:09 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Service
public class SearchIndexServiceImpl<T> implements SearchIndexService {
    private final RestHighLevelClient esClient;
    private final static Logger logger = LoggerFactory.getLogger(SearchIndexServiceImpl.class);

    public SearchIndexServiceImpl(RestHighLevelClient esClient) {
        this.esClient = esClient;
    }


    public <T> T wrapper(T source, SearchDocReq req) {
        return source;
    }

    /**
     * 分页获取文档内容
     *
     * @param req
     * @param indexName
     * @return
     */
    @Override
    public List<T> searchDocByPage(Class t, SearchDocReq req, String indexName) {
        Map<String, Object> map = new HashMap<>();
        Object o = req.getFieldClass();
        Class<?> sourceClass = o.getClass();
        for (Class<?> c = sourceClass; !c.equals(Object.class); c = c.getSuperclass()) {
            //获取全部字段
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                //跳过定义的静态和final变量
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                //设置可见性
                try {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    Object value = field.get(o);
                    map.put(fieldName, value);
                } catch (IllegalAccessException e) {
                    logger.info("get target value error.....");
                }

            }
        }
        //遍历map，添加BoolQueryBuilder
        BoolQueryBuilder boolQueryBuilder = null;

        if (CollectionUtil.isNotEmpty(map)) {
            boolQueryBuilder = QueryBuilders.boolQuery();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                //包含match的做模糊匹配，否则做精确匹配
                String key = entry.getKey();
                Object value = entry.getValue();
                if (key.contains("match") && null != value) {
                    BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
                    queryBuilder.should(QueryBuilders.wildcardQuery(key, "*" + value + "*"));
                    boolQueryBuilder.must(queryBuilder);
                }
                //做范围匹配,默认大于
                else if (key.contains("range") && null != value) {
                    boolQueryBuilder.must(QueryBuilders.rangeQuery(key).gt(value));
                }
                //TODO 做对象字段匹配
                else if (key.contains("list") && null != value) {

                }
                //做精确匹配
                else if (null != value) {
                    boolQueryBuilder.must(QueryBuilders.termQuery(key, value));
                }

            }


        }

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(indexName);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.size(req.getSize());
        searchSourceBuilder.from(req.getFrom());
        if (null != req.getSortBuilder()) {
            searchSourceBuilder.sort(req.getSortBuilder());
        }
        searchRequest.source(searchSourceBuilder);
        List<T> result = new ArrayList<>();
        try {
            SearchResponse response = esClient.search(searchRequest, RequestOptions.DEFAULT);
            if (null != response) {
                SearchHit[] hits = response.getHits().getHits();
                for (SearchHit hit : hits) {
                    String sourceAsString = hit.getSourceAsString();
                    T value = (T) OBJECT_MAPPER.readValue(sourceAsString, t);
                    result.add(value);
                }
                return result;
            }
        } catch (Exception e) {
            logger.info("esClient query error");
        }


        return null;
    }

    private static final ObjectMapper OBJECT_MAPPER = Optional.<ObjectMapper>empty().orElseGet(() -> {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    });

}

package com.weng.demo.springbootelasticsearch.controller;

import com.weng.demo.springbootelasticsearch.dto.DocumentAndType;
import com.weng.demo.springbootelasticsearch.dto.SearchDocReq;
import com.weng.demo.springbootelasticsearch.dto.SearchDocReqBuilder;
import com.weng.demo.springbootelasticsearch.dto.TestSearchFeild;
import com.weng.demo.springbootelasticsearch.queue.IndexQueue;
import com.weng.demo.springbootelasticsearch.resp.EsDocResp;
import com.weng.demo.springbootelasticsearch.service.CreateIndexService;
import com.weng.demo.springbootelasticsearch.service.SearchIndexService;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

/**
 * @DATE: 2022/2/15 8:07 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@RestController
public class ElasticSearchController {
    private final String INDEX_NAME="user_index_name";

    private final String TEST_INDEX_NAME = "weng_test_index_name";
    private final String user_weng_index_name = "user_weng_index_name";
    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private CreateIndexService createIndexService;

    @Autowired
    private IndexQueue indexQueue;

    @Autowired
    private SearchIndexService searchIndexService;


    @GetMapping("/testSearch")
    public String testSearch(){
        TestSearchFeild testSearchFeild = new TestSearchFeild();
        testSearchFeild.setDescr("生产时间范围");

        SearchDocReq build = new SearchDocReqBuilder().fieldClass(testSearchFeild).build();
        List<EsDocResp> list = searchIndexService.searchDocByPage(EsDocResp.class, build, user_weng_index_name);
        return list.toString();
    }

    /**
     * 迁移数据
     * @return
     * @throws IOException
     */
    @GetMapping("/testDataMove")
    public String testDataMove() throws IOException {

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询条件
        QueryBuilder queryBuilder = new MatchAllQueryBuilder();
        searchSourceBuilder.query(queryBuilder);

        //显示总命中数
        searchSourceBuilder.trackTotalHits(true);
        //分页，最多一万条数据
        searchSourceBuilder.from(1);
        searchSourceBuilder.size(9999);
        //设置超时时间
        searchSourceBuilder.timeout(new TimeValue(1000));
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits searchHits = searchResponse.getHits();
        TotalHits totalHits = searchHits.getTotalHits();

        System.out.println(totalHits.value);
        SearchHit[] hits = searchResponse.getHits().getHits();
        System.out.println(hits.length);
        for (SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            long [] userIdList = {1,3,4,5};
            sourceAsMap.put("test_weng_daxia",userIdList);
            createIndexService.createIndexByMap(sourceAsMap,TEST_INDEX_NAME);
        }
        return "success";
    }


    @GetMapping("/testGetEs")
    public String testGetEs() throws IOException {
 /*       org.elasticsearch.client.indices.GetIndexRequest indexRequest = new GetIndexRequest("user_index_name");
        GetIndexResponse getIndexResponse = client.indices().get(indexRequest, RequestOptions.DEFAULT);*/
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = new MatchAllQueryBuilder();
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.trackTotalHits(true);
        searchSourceBuilder.from(1);
        searchSourceBuilder.size(9999);
        searchSourceBuilder.timeout(new TimeValue(1000));
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits searchHits = searchResponse.getHits();
        TotalHits totalHits = searchHits.getTotalHits();

        System.out.println(totalHits.value);
        SearchHit[] hits = searchResponse.getHits().getHits();
        System.out.println(hits.length);

/*        for (int i = 0; i < 10; i++) {
            System.out.println("id :" + hits[i].getId()+"   value :"+hits[i].getSourceAsString());
        }*/


/*

        GetRequest getRequest = new GetRequest(INDEX_NAME,null);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        Map<String, Object> map = getResponse.getSourceAsMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {

        }
        System.out.println(getResponse.getSourceAsString());
        System.out.println(map.size());*/
        return "success";
    }

    @GetMapping("/testXBuilder")
    /**
     * 创建全文索引，根据field类型创建mapping
     *
     */
    public String testXBuilder(){
        try {
            XContentBuilder jsonBuilder = XContentFactory.jsonBuilder().startObject();
            long userId = 3L;
            String userName ="213";
            long [] userIdList = {1,3,4,5};

            List<Map<String,Object>> list = new ArrayList<>();
            DocumentAndType type = new DocumentAndType();

            //Object userInfo = new Object();

            jsonBuilder.field("userId",userId);
            jsonBuilder.field("userName",userName);
            jsonBuilder.field("userIdList",userIdList);
            //jsonBuilder.field("userInfo",userInfo);
            Map<String,Object> map = new HashMap<>();
            map.put("userId",userId);
            map.put("userName",userName);
            map.put("userIdList",userIdList);
            list.add(map);
            list.add(map);
            list.add(map);
            //nest type
            jsonBuilder.field("json_test_nest",list);
            jsonBuilder.endObject();

            indexQueue.addDocQueue(jsonBuilder,null,"user_weng_index_name",null);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success";
    }
}

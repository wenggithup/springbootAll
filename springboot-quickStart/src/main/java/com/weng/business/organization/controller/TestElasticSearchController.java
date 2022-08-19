package com.weng.business.organization.controller;

import org.apache.ibatis.session.SqlSession;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.MappingMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @DATE: 2022/2/15 7:27 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@RestController
public class TestElasticSearchController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @GetMapping("/testGetEs")
    public String testGetEs() throws IOException {
/*        GetIndexRequest indexRequest = new GetIndexRequest("user_index_name");
        GetIndexResponse getIndexResponse = restHighLevelClient.indices().get(indexRequest, RequestOptions.DEFAULT);

        Map<String, String> dataStreams = getIndexResponse.getDataStreams();
        System.out.println(dataStreams.size());

        Map<String, MappingMetadata> mappings = getIndexResponse.getMappings();
        for (Map.Entry<String, MappingMetadata> entry : mappings.entrySet()) {
            MappingMetadata value = entry.getValue();
            for (Map.Entry<String, Object> stringObjectEntry : value.getSourceAsMap().entrySet()) {
                System.out.println("MappingMetadata k:  "+ stringObjectEntry.getKey() + "MappingMetadata  value :"+stringObjectEntry.getValue());

            }
        }


        System.out.println(getIndexResponse.toString());*/
        return "success";
    }

//    @GetMapping("/testRestTemplate")
//    public String testRestTemplate(){
//        restTemplate.postForEntity("localhost:9011/quickStart/testGetEs",)
//    }

}

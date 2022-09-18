package com.weng.demo.springbootelasticsearch.service.impl;

import com.weng.demo.springbootelasticsearch.dto.DocumentAndType;
import com.weng.demo.springbootelasticsearch.service.CreateIndexService;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @DATE: 2022/2/21 3:59 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Service
public class CreateIndexServiceImpl implements CreateIndexService {
    private final RestHighLevelClient esClient;
    private final static Logger logger = LoggerFactory.getLogger(CreateIndexServiceImpl.class);

    public CreateIndexServiceImpl(RestHighLevelClient esClient) {
        this.esClient = esClient;
    }


    @Override
    public void createIndexByXBuilder(DocumentAndType documentAndType) {
        XContentBuilder doc = documentAndType.getDoc();
        String docId = documentAndType.getDocID();
        String indexName = documentAndType.getIndexName();

        try {
            addDoc(indexName, doc, docId);
        } catch (IOException e) {
            logger.debug("create index error");
        }


    }


    @Override
    public void createBulkIndexXBuilder(List<DocumentAndType> documentAndType) {
        try {
            bulkAddDoc(documentAndType);
        } catch (IOException e) {
            logger.debug("bulk create index error");
        }
    }

    @Override
    public void createIndexByMap(Map<String, Object> map, String indexName) {

        try {
            addDocByMap(map, indexName);
        } catch (Exception e) {
            logger.debug("create index by map error...");
        }


    }


    @Override
    public void createBulkIndexByMap(List<Map<String, Object>> list, String indexName) {
        try {
            addDocBulkByMap(list, indexName);
        } catch (Exception e) {
            logger.debug("bulk create index by map error...");
        }
    }

    private void addDocBulkByMap(List<Map<String, Object>> list, String indexName) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        if (CollectionUtil.isNotEmpty(list)) {
            for (Map<String, Object> objectMap : list) {
                IndexRequest indexRequest;
                String id = (String) objectMap.get("id");
                if (null != id) {
                    indexRequest = new IndexRequest().id(id).index(indexName).source(objectMap);
                } else {
                    indexRequest = new IndexRequest().index(indexName).source(objectMap);
                }
                bulkRequest.add(indexRequest);
            }


        }
        esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }


    /**
     * 通过kv添加索引
     *
     * @param map
     * @param indexName
     */
    private void addDocByMap(Map<String, Object> map, String indexName) throws IOException {
        IndexRequest indexRequest;
        String id = (String) map.get("id");
        if (null != id) {
            indexRequest = new IndexRequest().id(id).index(indexName).source(map);
        } else {
            indexRequest = new IndexRequest().index(indexName).source(map);
        }
        esClient.index(indexRequest, RequestOptions.DEFAULT);
    }


    /**
     * 添加索引
     *
     * @param indexName
     * @param doc
     * @param docId
     */
    private void addDoc(String indexName, XContentBuilder doc, String docId) throws IOException {
        IndexRequest request;
        if (StrUtil.isNotBlank(docId)) {
            request = new IndexRequest(indexName).id(docId).source(doc);
        } else {
            request = new IndexRequest(indexName).source(doc);
        }
        esClient.index(request, RequestOptions.DEFAULT);

    }


    /**
     * 批量添加索引
     *
     * @param list
     */
    private void bulkAddDoc(List<DocumentAndType> list) throws IOException {
        long tim = System.currentTimeMillis();
        BulkRequest bulkRequest = new BulkRequest();
        IndexRequest indexRequest;
        for (DocumentAndType documentAndType : list) {
            XContentBuilder doc = documentAndType.getDoc();
            String docId = documentAndType.getDocID();
            String indexName = documentAndType.getIndexName();

            if (StrUtil.isNotBlank(docId)) {
                indexRequest = new IndexRequest(indexName).id(docId).source(doc);
            } else {
                indexRequest = new IndexRequest(indexName).source(doc);
            }
            bulkRequest.add(indexRequest);
        }

        esClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        logger.debug("#BulkAddDoc is ok cost time is " + (System.currentTimeMillis() - tim) + ".................... ");
    }
}

package com.weng.demo.springbootelasticsearch.service.impl;

import com.weng.demo.springbootelasticsearch.dto.DocumentAndType;
import com.weng.demo.springbootelasticsearch.service.UpdateIndexService;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @DATE: 2022/2/22 10:35 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Service
public class UpdateIndexServiceImpl implements UpdateIndexService {
    private final  static Logger logger = LoggerFactory.getLogger(UpdateIndexServiceImpl.class);


    private final RestHighLevelClient esClient;

    public UpdateIndexServiceImpl(RestHighLevelClient esClient) {
        this.esClient = esClient;
    }


    @Override
    public void updateIndex(DocumentAndType documentAndType) {
        logger.info("updateIndex start。。。");
        String docId = documentAndType.getDocID();
        String indexName = documentAndType.getIndexName();
        XContentBuilder doc = documentAndType.getDoc();

        UpdateRequest updateRequest = new UpdateRequest(indexName, docId );
        updateRequest.doc(doc);
        updateRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);
        try {
            esClient.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateBulkIndex(List<DocumentAndType> documentAndType) {
       BulkRequest bulkRequest = new BulkRequest();
        long tim = System.currentTimeMillis();
        try {
            for (DocumentAndType doc : documentAndType) {
                UpdateRequest request = new UpdateRequest(doc.getIndexName(), doc.getDocID());
                request.doc(doc.getDoc());
                bulkRequest.add(request);
            }
            esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            if (logger.isDebugEnabled()) {
                logger.debug("#BulkAddDoc is ok cost time is " + (System.currentTimeMillis() - tim) + ".................... ");
            }
        } catch (Exception e) {
            logger.error("bulkAddUpdate get a exception", e);
        }

    }
}

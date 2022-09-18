package com.weng.demo.springbootelasticsearch.queue;

import com.weng.demo.springbootelasticsearch.dto.DocumentAndType;
import com.weng.demo.springbootelasticsearch.service.CreateIndexService;
import com.weng.demo.springbootelasticsearch.service.UpdateIndexService;
import com.weng.demo.springbootelasticsearch.util.ThreadPoolUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @DATE: 2022/2/21 3:28 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description: 索引队列，防止数据量突然激增
 */
@Component
public class IndexQueue {
    private static final Logger logger = LoggerFactory.getLogger(IndexQueue.class.getName());
    private final BlockingQueue<DocumentAndType> createQueue = new LinkedBlockingDeque<>();
    private final BlockingQueue<DocumentAndType> updateQueue = new LinkedBlockingDeque<>();

    public final static int BULK_CREATE_INDEX_SIZE = 100;//批量建立全文检索，每次批量提交的数量

    private final CreateIndexService createIndex;
    private final UpdateIndexService updateIndex;

    public IndexQueue(CreateIndexService createIndex, UpdateIndexService updateIndex) {
        this.createIndex = createIndex;
        this.updateIndex = updateIndex;
        // 创建索引时创建一个线程池消费待创建索引队列数据
        ThreadPoolUtil.newCreateIndexSingleThreadPool().execute(new IndexCreate());
        // 更新索引时创建一个线程池消费待修改索引队列数据
        ThreadPoolUtil.newUpdateIndexSingleThreadPool().execute(new IndexUpdate());
    }


    /**
     * 添加索引至阻塞队列中
     *
     * @param doc
     * @param indexType
     * @param indexName
     * @param docId
     */
    public void addDocQueue(XContentBuilder doc, String indexType, String indexName, String docId) {
        synchronized (this) {
            DocumentAndType infoDoc = new DocumentAndType();
            if (!StrUtil.isNotBlank(docId)) {
                infoDoc.setDocID(docId);
            }
            infoDoc.setDoc(doc);
            infoDoc.setIndexName(indexName);
            infoDoc.setIndexType(indexType);
            if (!createQueue.offer(infoDoc)) {
                DocumentAndType oldObj = createQueue.poll();
                if (logger.isInfoEnabled()) {
                    logger.info("队列已满，删除第一个元素" + oldObj);
                }
                createQueue.offer(infoDoc);
            }
        }
    }


    /**
     * 添加更新索引队列
     *
     * @param builder
     * @param docList
     * @param indexType
     * @param indexName
     */
    public void addUpdateDocQueue(BoolQueryBuilder builder, List<XContentBuilder> docList, String indexType, String indexName) {
        synchronized (this) {
            DocumentAndType infoDoc = new DocumentAndType();
            infoDoc.setBuilder(builder);
            infoDoc.setDocList(docList);
            infoDoc.setIndexName(indexName);
            infoDoc.setIndexType(indexType);
            if (!updateQueue.offer(infoDoc)) {
                DocumentAndType oldObj = updateQueue.poll();
                if (logger.isInfoEnabled()) {
                    logger.info("#IndexQueue-addUpdateDoc:队列已满，删除第一个元素" + oldObj);
                }
                updateQueue.offer(infoDoc);
            }
        }
    }


    /**
     * 创建索引执行线程，将待推送的文档放至队列
     */
    private class IndexCreate implements Runnable {
        @Override
        public void run() {
            long startTime;
            while (true) {
                startTime = System.currentTimeMillis();
                DocumentAndType documentAndType;
                try {
                    if (logger.isInfoEnabled()) {
                        logger.info("队列数量中存储的数据数量为count=" + createQueue.size() + "..........................");
                    }
                    if (createQueue.size() >= BULK_CREATE_INDEX_SIZE) {
                        if (logger.isInfoEnabled()) {
                            logger.info("队列数量超出预定值，开始批量处理..........................");
                        }
                        List<DocumentAndType> docList = new ArrayList<>();
                        for (int i = 0; i < BULK_CREATE_INDEX_SIZE; i++) {
                            docList.add(createQueue.take());
                        }
                        createIndex.createBulkIndexXBuilder(docList);
                    } else {
                        documentAndType = createQueue.take();
                        createIndex.createIndexByXBuilder(documentAndType);
                    }
                    if (logger.isInfoEnabled()) {
                        logger.info("创建全文检索花费时间为" + (System.currentTimeMillis() - startTime));
                    }

                } catch (Exception e) {
                    if (logger.isErrorEnabled()) {
                        logger.error("创建全文检索异常：" + e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * 更新索引执行线程
     */
    private class IndexUpdate implements Runnable {
        @Override
        public void run() {
            long startTime;
            while (true) {
                startTime = System.currentTimeMillis();
                DocumentAndType documentAndType;
                try {
                    if (logger.isInfoEnabled()) {
                        logger.info("队列数量中存储的数据数量为count=" + createQueue.size() + "..........................");
                    }
                    if (createQueue.size() >= BULK_CREATE_INDEX_SIZE) {
                        if (logger.isInfoEnabled()) {
                            logger.info("队列数量超出预定值，开始批量处理..........................");
                        }
                        List<DocumentAndType> docList = new ArrayList<>();
                        for (int i = 0; i < BULK_CREATE_INDEX_SIZE; i++) {
                            docList.add(createQueue.take());
                        }
                        createIndex.createBulkIndexXBuilder(docList);
                    } else {
                        documentAndType = createQueue.take();
                        createIndex.createIndexByXBuilder(documentAndType);
                    }
                    if (logger.isInfoEnabled()) {
                        logger.info("创建全文检索花费时间为" + (System.currentTimeMillis() - startTime));
                    }

                } catch (Exception e) {
                    if (logger.isErrorEnabled()) {
                        logger.error("创建全文检索异常：" + e.getMessage());
                    }
                }
            }
        }
    }
}

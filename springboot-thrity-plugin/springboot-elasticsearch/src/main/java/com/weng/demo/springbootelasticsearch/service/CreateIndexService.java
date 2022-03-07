package com.weng.demo.springbootelasticsearch.service;

import com.weng.demo.springbootelasticsearch.dto.DocumentAndType;

import java.util.List;
import java.util.Map;

/**
 * @DATE: 2022/2/21 3:57 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public interface CreateIndexService {
    /**
     * 创建单条索引
     * @param documentAndType
     */
    void createIndexByXBuilder(DocumentAndType documentAndType);


    /**
     * 批量创建索引
     * @param documentAndType
     */
    void createBulkIndexXBuilder(List<DocumentAndType> documentAndType);


    void createIndexByMap(Map<String, Object> map, String indexName);


    void createBulkIndexByMap(List<Map<String, Object>> list, String indexName);
}

package com.weng.demo.springbootelasticsearch.service;

import com.weng.demo.springbootelasticsearch.dto.SearchDocReq;

import java.util.List;

/**
 * @DATE: 2022/3/11 11:04 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public interface SearchIndexService<T> {

    List<T> searchDocByPage(Class t, SearchDocReq req, String indexName);
}

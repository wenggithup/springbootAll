package com.weng.demo.springbootelasticsearch.service;

import com.weng.demo.springbootelasticsearch.dto.DocumentAndType;

import java.util.List;

/**
 * @DATE: 2022/2/22 10:30 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public interface UpdateIndexService {
    /**
     * 创建单条索引
     *
     * @param documentAndType
     */
    void updateIndex(DocumentAndType documentAndType);


    /**
     * 批量创建索引
     *
     * @param documentAndType
     */
    void updateBulkIndex(List<DocumentAndType> documentAndType);
}

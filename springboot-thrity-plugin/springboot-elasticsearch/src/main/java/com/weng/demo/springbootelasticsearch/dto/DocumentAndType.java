package com.weng.demo.springbootelasticsearch.dto;

import lombok.Data;
import lombok.ToString;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;

import java.util.List;

/**
 * @DATE: 2022/2/21 3:30 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description: 文档处理类型
 */
@Data
@ToString
public class DocumentAndType {

    private XContentBuilder doc;
    private String indexName;
    private String indexType;
    private BoolQueryBuilder builder;
    private String docID;
    List<XContentBuilder> docList;
}

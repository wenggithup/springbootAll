package com.weng.demo.springbootelasticsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.search.sort.SortBuilder;

/**
 * @DATE: 2022/3/11 10:52 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Data
@AllArgsConstructor
public class SearchDocReq<T> {

    private int size;

    private int from;

    private SortBuilder sortBuilder;

    private T fieldClass;
}

package com.weng.demo.springbootelasticsearch.dto;

import lombok.Data;
import org.elasticsearch.search.sort.SortBuilder;

/**
 * @DATE: 2022/3/11 10:37 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class SearchDocReqBuilder<T> {

    private int _size = 100;

    private int _from = 1;

    private SortBuilder _sortBuilder;

    private T _fieldClass;

    public SearchDocReqBuilder() {
    }

    public SearchDocReq build() {
        return new SearchDocReq(_size, _from, _sortBuilder, _fieldClass);
    }

    public SearchDocReqBuilder size(int size) {
        this._size = size;
        return this;
    }

    public SearchDocReqBuilder from(int from) {
        this._from = from;
        return this;
    }

    public SearchDocReqBuilder sortBuilder(SortBuilder sortBuilder) {
        this._sortBuilder = sortBuilder;
        return this;
    }

    public SearchDocReqBuilder fieldClass(T fieldClass) {
        this._fieldClass = fieldClass;
        return this;
    }
}

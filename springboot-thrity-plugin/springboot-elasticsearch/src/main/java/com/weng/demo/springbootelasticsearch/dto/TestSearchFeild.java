package com.weng.demo.springbootelasticsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @DATE: 2022/3/11 2:26 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestSearchFeild {
    private String descr;
    private String name_match;

    private Long area;
}

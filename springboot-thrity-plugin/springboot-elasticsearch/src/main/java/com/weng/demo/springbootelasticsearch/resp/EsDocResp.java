package com.weng.demo.springbootelasticsearch.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @DATE: 2022/3/11 2:52 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsDocResp {

    @JsonProperty("createtime")
    private String createtime;
    @JsonProperty("descr")
    private String descr;
    @JsonProperty("content")
    private String content;
    @JsonProperty("area")
    private Integer area;
}

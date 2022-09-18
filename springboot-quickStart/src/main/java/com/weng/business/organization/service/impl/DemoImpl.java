package com.weng.business.organization.service.impl;

import lombok.Data;
import lombok.ToString;

/**
 * @DATE: 2022/8/26 4:07 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Data
@ToString
public class DemoImpl {
    private String name ="test";
    private String pwd = "2131231";


    public String testDemoImpl(){
        return new DemoImpl().toString();
    }
}

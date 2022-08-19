package com.weng.business.organization.service;

/**
 * @DATE: 2022/7/27 3:42 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public interface TestListener {

    void testListener(String str,TestInerface testInerface);
    default void testDefalut(){
        System.out.println("dsada");
    }
}

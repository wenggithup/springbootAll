package com.weng.demo.impl;

import com.weng.demo.SpiInterface;

/**
 * @DATE: 2022/1/18 4:42 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class SpiImplTwo implements SpiInterface {
    @Override
    public void testSpiInfo(String name) {

        System.out.println("this is SpiImplTwo info");
    }
}

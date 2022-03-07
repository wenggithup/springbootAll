package com.demo.weng;

import com.weng.demo.SpiInterface;

import java.util.ServiceLoader;

/**
 * @DATE: 2022/1/18 4:47 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class testSpi {
    public static void main(String[] args) {
        ServiceLoader<SpiInterface> load = ServiceLoader.load(SpiInterface.class);
        for (SpiInterface spiInterface : load) {

            spiInterface.testSpiInfo("123");
        }
    }
}

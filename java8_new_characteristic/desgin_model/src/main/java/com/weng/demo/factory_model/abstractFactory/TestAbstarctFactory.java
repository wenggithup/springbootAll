package com.weng.demo.factory_model.abstractFactory;

/**
 * @DATE: 2022/8/31 4:36 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class TestAbstarctFactory {

    public static void main(String[] args) {
        ComputerFactory factory= new  AmdComputerFactory();
        Computer computer = factory.getComputer();
    }
}

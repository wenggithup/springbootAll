package com.weng.demo.factory_model.simpleFactory;

/**
 * @DATE: 2022/8/23 4:45 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class PepperPizza extends Pizza{

    @Override
    public void prepare() {
        setName("PepperPizza");
        super.prepare();
    }
}

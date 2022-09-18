package com.weng.demo.factory_model.simpleFactory;

/**
 * @DATE: 2022/8/23 4:47 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class SpicyPizza extends Pizza{

    @Override
    public void prepare() {
        setName("SpicyPizza");
        super.prepare();
    }
}

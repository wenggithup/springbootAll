package com.weng.demo.factory_model.methodFactory;

/**
 * @DATE: 2022/8/24 10:34 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class BjCheesePizza extends Pizza {

    @Override
    public void prepare() {
        setName("BjCheesePizza");
        super.prepare();
    }
}

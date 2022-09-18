package com.weng.demo.factory_model.methodFactory;

/**
 * @DATE: 2022/8/24 10:35 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class ShPepperPizza extends Pizza {
    @Override
    public void prepare() {
        setName("BjPepperPizza");
        super.prepare();
    }
}

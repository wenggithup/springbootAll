package com.weng.demo.factory_model.methodFactory;

/**
 * @DATE: 2022/8/24 10:41 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class BjPizza extends OrderPizza{


    @Override
    Pizza getOrderPizza(String type) {
        Pizza pizza = null;
        if (type.equals("cheese")) {
            pizza = new BjCheesePizza();
        } else if (type.equals("pepper")) {
            pizza = new BjPepperPizza();
        }
        return pizza;
    }
}

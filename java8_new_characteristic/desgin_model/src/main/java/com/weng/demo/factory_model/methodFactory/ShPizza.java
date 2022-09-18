package com.weng.demo.factory_model.methodFactory;

/**
 * @DATE: 2022/8/24 10:42 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class ShPizza extends OrderPizza{
    @Override
    Pizza getOrderPizza(String type) {
        Pizza pizza = null;
        if (type.equals("cheese")) {
            pizza = new ShCheesePizza();
        } else if (type.equals("pepper")) {
            pizza = new ShPepperPizza();
        }
        return pizza;
    }
}

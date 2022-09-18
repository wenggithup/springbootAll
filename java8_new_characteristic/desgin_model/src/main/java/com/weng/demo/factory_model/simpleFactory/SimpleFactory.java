package com.weng.demo.factory_model.simpleFactory;

/**
 * @DATE: 2022/8/23 4:56 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class SimpleFactory {

    public Pizza createPizza(String type){
        Pizza pizza = null ;
        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (type.equals("pepper")) {
            pizza = new PepperPizza();
        } else if (type.equals("spicy")) {
            pizza = new SpicyPizza();
        }

        return pizza;
    }
}

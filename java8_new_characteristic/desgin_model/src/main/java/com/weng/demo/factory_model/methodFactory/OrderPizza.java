package com.weng.demo.factory_model.methodFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @DATE: 2022/8/24 10:37 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public abstract class OrderPizza {


    public OrderPizza() {
        do {
        Pizza pizza = null;
        String type = getType();
        pizza = this.getOrderPizza(type);
        pizza.prepare();
        pizza.cooking();
        pizza.cut();
        pizza.transfer();
        }while (true);

    }

    /**
     * 获取pizza接口
     * @param type
     * @return
     */
    abstract Pizza getOrderPizza(String type);

    private  String getType(){
        BufferedReader string = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("input type of pizza");
        String s = null;
        try {
            s = string.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}

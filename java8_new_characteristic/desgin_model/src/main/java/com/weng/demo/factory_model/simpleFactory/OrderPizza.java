package com.weng.demo.factory_model.simpleFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @DATE: 2022/8/23 4:47 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class OrderPizza {
    /**
     * 这个时候如果新增pizza种类的话，只需要在SimpleFactory新增对应的判断即可
     */
    public OrderPizza() {
        while (true) {
            String type = getType();
            SimpleFactory simpleFactory = new SimpleFactory();
            Pizza pizza = simpleFactory.createPizza(type);
            pizza.prepare();
            pizza.cooking();
            pizza.cut();
            pizza.transfer();
        }
    }

    public static void main(String[] args) {
//        new OrderPizza();

    }

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

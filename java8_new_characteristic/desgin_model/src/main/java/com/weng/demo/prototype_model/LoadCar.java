package com.weng.demo.prototype_model;

import java.util.HashMap;

/**
 * @DATE: 2022/8/31 5:10 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class LoadCar {

    private static HashMap<String,Car> hashMap = new HashMap<>();

    static {
        loadCar();
    }

    private static void loadCar() {
        Bicycle bicycle = new Bicycle();
        hashMap.put("1",bicycle);

        Motorcycle motorcycle = new Motorcycle();
        hashMap.put("2",motorcycle);

    }

    public static Car getCar(String type) throws CloneNotSupportedException {
        Car car = hashMap.get(type);
//        return car;
        //使用原型模式避免此对象被别人修改
        return (Car) car.clone();
    }

}

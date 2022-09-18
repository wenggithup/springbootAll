package com.weng.demo.prototype_model;

/**
 * @DATE: 2022/8/31 5:14 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class TestPrototypeModel {

    public static void main(String[] args) throws CloneNotSupportedException {
        Car car1 = LoadCar.getCar("1");

        System.out.println(car1.name);
        car1.name = "test";
        //这里如果不使用拷贝，且该对象呗多处调用时，值容易发生改变
        Car car2 = LoadCar.getCar("1");
        System.out.println(car2.name);
        Car car3 = LoadCar.getCar("2");
        System.out.println(car3.name);
    }
}

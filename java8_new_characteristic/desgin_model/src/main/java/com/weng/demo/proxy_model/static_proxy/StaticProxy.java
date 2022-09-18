package com.weng.demo.proxy_model.static_proxy;

/**
 * @DATE: 2022/9/7 5:48 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class StaticProxy {

    public static void main(String[] args) {
        Car car = new Benz();
        Car car1 = new CarHandler(car);
        car1.run();
    }

    interface Car{
        void run();
    }

    static class Benz implements Car {


        @Override
        public void run() {
            System.out.println("Benz is run.....");
        }
    }

    static class CarHandler implements Car{
        private Car car;
        public CarHandler(Car car){
            this.car = car;
        }
        @Override
        public void run(){
            System.out.println("handler before...");
            car.run();
            System.out.println("handler after...");
        }
    }
}

package com.weng.demo.singleton;

/**
 * @DATE: 2022/8/14 3:04 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:懒汉单例模式
 */
public class LazySingleton {
    public static void main(String[] args) {
        LazySingletonOne instance1 = LazySingletonOne.getInstance();
        LazySingletonOne instance2 = LazySingletonOne.getInstance();
        System.out.println(instance1 == instance2);
    }
}

/**
 * 同饿汉模式，只是在调用的时候初始化
 * 这种线程不安全
 */
class LazySingletonOne{
    private static LazySingletonOne lazySingletonOne;

    private LazySingletonOne(){

    }

    public static LazySingletonOne getInstance(){
        if (null == lazySingletonOne){
            lazySingletonOne = new LazySingletonOne();
        }
        return lazySingletonOne;
    }

}

class LazySingletonTwo{
    private LazySingletonTwo(){

    }

    private static LazySingletonTwo two;

    public static synchronized LazySingletonTwo getInstance(){
        if (null == two){
            two = new LazySingletonTwo();
        }
        return two;
    }
}
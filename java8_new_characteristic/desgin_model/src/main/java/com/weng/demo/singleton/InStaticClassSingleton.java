package com.weng.demo.singleton;

/**
 * @DATE: 2022/8/14 3:30 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:静态内部内生成单例模式
 * 特点：1、静态内部内在外部内加载的时候不会加载，只有调用的时候才会加载。
*      2、静态内部内在加载的时候是线程安全的
 */
public class InStaticClassSingleton {


    public static void main(String[] args) {
        InStaticClassSingletonOne instance = InStaticClassSingletonOne.getInstance();
        InStaticClassSingletonOne instance1 = InStaticClassSingletonOne.getInstance();
        System.out.println(instance == instance1);
    }
}

 class InStaticClassSingletonOne{

    private InStaticClassSingletonOne(){

    }

    public static InStaticClassSingletonOne getInstance(){
        return StaticClassSingle.one;
    }

private static   class StaticClassSingle{
    private static InStaticClassSingletonOne one = new InStaticClassSingletonOne();

}
}

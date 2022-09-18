package com.weng.demo.singleton_model;

/**
 * @DATE: 2022/8/14 2:48 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:饿汉模式单例
 */
public class HungrySingleton {

    public static void main(String[] args) {
        HungrySingletonTypeOne instance = HungrySingletonTypeOne.getInstance();
        HungrySingletonTypeOne instance1 = HungrySingletonTypeOne.getInstance();
        System.out.println(instance == instance1);
    }

}
/**
 * 饿汉单例模式
 * 1、构造器私有化
 * 2、类的内部创建对象
 * 3、向外暴露接口
 */
class HungrySingletonTypeOne{

    private static final    HungrySingletonTypeOne single = new HungrySingletonTypeOne();

    /**
     * 构造器私有化
     */
    private  HungrySingletonTypeOne(){

    }

    /**
     * 提供对外方法
     */
    public static HungrySingletonTypeOne getInstance(){
        return single;
    }

}

class HungrySingletonTypeTwo{
    private final static HungrySingletonTypeTwo single;
    static {
        single = new HungrySingletonTypeTwo();
    }

    private HungrySingletonTypeTwo(){

    }

    public static HungrySingletonTypeTwo getInstance(){
        return single;
    }

}

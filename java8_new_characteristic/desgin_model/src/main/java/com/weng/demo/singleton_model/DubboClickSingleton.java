package com.weng.demo.singleton_model;

/**
 * @DATE: 2022/8/14 3:16 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:双重检查单例模式
 */
public class DubboClickSingleton {
    private static  DubboClickSingletonOne instance2;
    private static  DubboClickSingletonOne instance3;
    private static  DubboClickSingletonOne instance4;
    private static  DubboClickSingletonOne instance5;
    public static void main(String[] args) {
        //单线程的情况
        DubboClickSingletonOne instance = DubboClickSingletonOne.getInstance();
        DubboClickSingletonOne instance1 = DubboClickSingletonOne.getInstance();
        System.out.println(instance == instance1);

        //多线程的情况下

        Thread thread = new Thread(()->{
            instance2 = DubboClickSingletonOne.getInstance();
        },"1");
        Thread thread2 = new Thread(()->{
            instance3 = DubboClickSingletonOne.getInstance();
        },"2");
        Thread thread3 = new Thread(()->{
            instance4 = DubboClickSingletonOne.getInstance();
        },"3");
        Thread thread4 = new Thread(()->{
            instance5 = DubboClickSingletonOne.getInstance();
        },"4");
        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();

        System.out.println(instance2.hashCode());
        System.out.println(instance3.hashCode());
        System.out.println(instance4.hashCode());
        System.out.println(instance5.hashCode());
    }


}

class DubboClickSingletonOne{
    private DubboClickSingletonOne(){

    }

    /**
     * wen，这样变量修改具有可见性，修改了值后，另一个线程立马感知到
     */
    private static volatile DubboClickSingletonOne one;

    public static DubboClickSingletonOne getInstance(){
        if (null == one){
            synchronized (DubboClickSingletonOne.class){
                if (null == one){
                    one = new DubboClickSingletonOne();
                }
            }
        }
        return one;
    }

}
package com.boot.demo;

/**
 * DATE: 2021/9/26 2:14 下午
 * Author: WengChuanJie
 */
public class TestFuncitonClass{


    public static int compareTestTo(Integer ao) {
        return ao.compareTo(1);
    }

    public static int compareTestTo(TestFuncitonClass testFuncitonClass) {
       return testFuncitonClass.compareTestTo(1);
    }
}

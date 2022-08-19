package com.demo.weng.generic;

/**
 * @DATE: 2021/11/12 10:15 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:未传入泛型实参时，与泛型类的定义相同，在声明类的时候，需将泛型的声明也一起加到类中
 */
public class GenericInterfaceImpl<T,V> implements GenericInterface<T,V>{
    private String s1  = "";
    private String s2;

    @Override
    public T getInfo(V info) {
        System.out.println(s1);
        System.out.println(s2);
        return null;
    }
}

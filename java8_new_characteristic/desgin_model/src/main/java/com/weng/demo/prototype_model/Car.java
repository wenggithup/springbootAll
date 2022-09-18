package com.weng.demo.prototype_model;

/**
 * @DATE: 2022/8/31 5:06 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class Car implements Cloneable{

    protected String name;






    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

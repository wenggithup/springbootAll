package com.weng.demo.proxy_model.dynamic_proxy.cglib;

/**
 * @DATE: 2022/9/14 7:32 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class Teacher implements People{

    public String teach(){
        System.out.println(" Teach .....");
        return "123dasda";
    }

    @Override
    public void run() {
        System.out.println("teacher run.....");
    }
}

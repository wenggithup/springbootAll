package com.weng.demo.builder_model;

/**
 * @DATE: 2022/8/31 4:51 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class TestBuilderModel {

    public static void main(String[] args) {
        User user = User.builder().
                img("123").name("2131").label("tsad").build();
        System.out.println(user);
    }
}

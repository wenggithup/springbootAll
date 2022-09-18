package com.weng.demo.factory_model.simpleFactory;

/**
 * @DATE: 2022/8/23 4:40 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */

public class Pizza {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void cooking(){
        System.out.println(this.getName() +" 正在cooking中。。。。。。");
    }
    public void prepare(){
        System.out.println(this.getName() +" 正在prepare中。。。。。。");
    }
    public void cut(){
        System.out.println(this.getName() +" 正在cut中。。。。。。");
    }
    public void transfer(){
        System.out.println(this.getName() +" 正在transfer中。。。。。。");
    }
}

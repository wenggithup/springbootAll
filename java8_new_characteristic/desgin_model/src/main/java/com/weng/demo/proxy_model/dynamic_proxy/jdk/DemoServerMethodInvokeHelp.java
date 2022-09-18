package com.weng.demo.proxy_model.dynamic_proxy.jdk;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

/**
 * @DATE: 2022/9/8 9:45 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class DemoServerMethodInvokeHelp {
    /**
     * 拦截器链
     */
    private  List<DemoServerMethodInvoke> invokeList = new ArrayList<>();

    public  List<DemoServerMethodInvoke> getInvokeList(){
        return this.invokeList;
    }


    /**
     * 通过spi机制加载实现类
     */
    private void loadInvoke() {
        ServiceLoader<DemoServerMethodInvoke> load = ServiceLoader.load(DemoServerMethodInvoke.class);
        for (DemoServerMethodInvoke demoServerMethodInvoke : load) {
            invokeList.add(demoServerMethodInvoke);
        }
        //对invokeList进行排序
        invokeList = invokeList.stream().sorted(Comparator.comparing(DemoServerMethodInvoke::getOrder).reversed()).collect(Collectors.toList());
    }





    private DemoServerMethodInvokeHelp(){
        loadInvoke();
    }

    private  static volatile  DemoServerMethodInvokeHelp help;
    /**
     * 单例
     * @return
     */
    public static DemoServerMethodInvokeHelp getInstance(){
        if (null == help){
            synchronized (DemoServerMethodInvokeHelp.class){
                if (null == help){
                    help = new DemoServerMethodInvokeHelp();
                }
            }
        }
        return help;
    }


}

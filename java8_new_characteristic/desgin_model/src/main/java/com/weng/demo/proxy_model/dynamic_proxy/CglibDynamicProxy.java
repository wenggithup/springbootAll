package com.weng.demo.proxy_model.dynamic_proxy;

import com.weng.demo.proxy_model.dynamic_proxy.cglib.CglibFactory;
import com.weng.demo.proxy_model.dynamic_proxy.cglib.People;
import com.weng.demo.proxy_model.dynamic_proxy.cglib.Teacher;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @DATE: 2022/9/8 5:04 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class CglibDynamicProxy {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        People teacher = CglibFactory.createCglibProxy(Teacher.class);
        teacher.run();
//        Constructor<Teacher> constructor = Teacher.class.getDeclaredConstructor();
//        constructor.setAccessible(true);
//        Teacher teacher = constructor.newInstance();
//        teacher.teach();

    }







}

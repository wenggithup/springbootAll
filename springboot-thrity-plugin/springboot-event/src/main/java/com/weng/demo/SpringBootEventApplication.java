package com.weng.demo;

import com.weng.demo.controller.TestController;
import com.weng.demo.event.MyCustomizeEvent;
import com.weng.demo.event.PushEventProducer;
import com.weng.demo.listener.MyCustomizeListener;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @DATE: 2022/7/28 10:22 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@SpringBootApplication
public class SpringBootEventApplication {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        SpringApplication.run(SpringBootEventApplication.class, args);
       /* List<String> list = SpringFactoriesLoader.loadFactoryNames(ApplicationContextAware.class,  ApplicationContextAware.class.getClassLoader());
//        System.out.println(list);
//        ServiceLoader<ApplicationListener> applicationListeners = ServiceLoader.load(ApplicationListener.class);
        List<Class<?>> listClass = new ArrayList<>();
        for (String applicationListener : list) {
            Class<?> aClass = ClassUtils.forName(applicationListener,ApplicationListener.class.getClassLoader());
//            Object o = aClass.newInstance();
            listClass.add(aClass);
        }

        ApplicationListener listener =(ApplicationListener) Proxy.newProxyInstance(ApplicationListener.class.getClassLoader(), new Class[]{ApplicationListener.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println(method.getName());
                for (Class<?> o1 : listClass) {
                  return   method.invoke(o1.newInstance(),objects);
                }
                return null;
            }
        });

        listener.onApplicationEvent(new MyCustomizeEvent("asd"));*/
    }

}

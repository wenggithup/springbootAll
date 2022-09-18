package com.weng.demo.datasource.aop;

import com.weng.demo.datasource.annotation.DynamicSource;
import com.weng.demo.datasource.factory.DynamicDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @DATE: 2022/9/14 3:40 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Component
@Aspect
public class DynamicAdvice {
//    @Before(value = "execution(* com.example.demo..*..*(..))")

    /**
     * @annotation() 只针对方法生效，对class注解不生效
     */
    @Pointcut(value = "execution(* com.example.demo..*..*(..))")
    public void annotationCut(){};
    @Before(value = "annotationCut()")
    public void before(JoinPoint joinPoint) {
        try {
            MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();


            Method method = methodSignature.getMethod();
            Class<?> declaringClass = method.getDeclaringClass();
            DynamicSource dynamicSource = declaringClass.getAnnotation(DynamicSource.class);

            DynamicSource annotation = method.getAnnotation(DynamicSource.class);
            if (null== dynamicSource && null == annotation){
                return;
            }
            if (null != annotation){
                dynamicSource = annotation;
            }

            //获取 dataSourceId值
            String dataSourceId = dynamicSource.value();
            System.out.println(dataSourceId);
            DynamicDataSource.setThreadLocal(dataSourceId);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}

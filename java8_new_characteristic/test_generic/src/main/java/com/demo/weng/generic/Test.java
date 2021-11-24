package com.demo.weng.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @DATE: 2021/11/12 9:48 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:泛型测试
 */
public class Test {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        GenericClass<Integer> genericInteger = new GenericClass<>();
        genericInteger.setKey(123);

        GenericClass genericClass1= new GenericClass();
        genericClass1.setKey(123);

        ArrayList list  = new ArrayList();


        //接口
        GenericInterface<String,Integer> genericInterface = new GenericInterface<String, Integer>() {
            @Override
            public String getInfo(Integer info) {
                return null;
            }
        };


        /**
         * 验证泛型可否用子类代替，例：Integer是Number的子类
         * 同一种泛型可以对应多个版本（因为参数类型是不确定的），不同版本的泛型类实例是不兼容的。
         */
        GenericClass<Integer> genericIntegerInteger = new GenericClass<>();

        GenericClass<Number> numberGenericClass = new GenericClass<>();

        show(numberGenericClass);

        showDiff(numberGenericClass);
        showDiff(genericIntegerInteger);


        /**
         * 根据class
         */
        GenericInterfaceImpl genericInterface1 = GenericClass.showClass(GenericInterfaceImpl.class);
    }

    private static void show(GenericClass<Number> numberGenericClass) {
        return;
    }

    private static void showDiff(GenericClass<?> numberGenericClass) {
        return;
    }


}

package com.demo.weng.generic;

/**
 * @DATE: 2021/11/12 9:37 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description: 泛型类，
 * //此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
 * //在实例化泛型类时，必须指定T的具体类型
 */
public class GenericClass<k> {
    private k key;

    public GenericClass() {

    }

    public GenericClass(k key) {
        this.key = key;
    }
    //虽然在方法中使用了泛型，但是这并不是一个泛型方法。这只是类中一个普通的成员方法，只不过他的返回值是在声明泛型类已经声明过的泛型。
    public k getKey() {
        return key;
    }

    public void setKey(k key) {
        this.key = key;
    }

    /**
     *  这才是一个真正的泛型方法。
     *      * 首先在public与返回值之间的<T>必不可少，这表明这是一个泛型方法，并且声明了一个泛型T
     * @param index
     * @param <T>
     * @return
     */
    public <T,V,Z> k show(k index){
        return index;
    }

    public <T> void showDiff(T t){
        return;
    }

    public static  <T> T showClass(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        T t = tClass.newInstance();
        System.out.println(t.toString());
        return t;

    }
}

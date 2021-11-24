package com.boot.demo;

import org.apache.logging.log4j.util.PropertySource;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * DATE: 2021/9/24 3:21 下午
 * Author: WengChuanJie
 */
public class Java8Test {


    public static void main(String[] args) {

        /**
         * 创建流
         * */
        //createStream();

        /**
         * 操作流
         */
        // filterStream();

        /**
         * 映射流
         */
        // mapStream();

        /**
         * 匹配流
         */
        // matchStream();

        /**
         * 归纳
         */

        reduceStream();

    }

    private static void reduceStream() {
        List<String> props = Arrays.asList("profile=native", "debug=true", "logging=warn", "interval=500");


        Map<String, String> collect = props.stream().collect(Collectors.toMap(t -> t.split("\\=", 2)[0], t -> t.split("\\=", 2)[1]));
        // 把所有Map聚合到一个Map:;
        collect.forEach((k, v) -> {
            System.out.println("k =" + k + "  v=" + v);
        });

        props.stream().map(kv -> Collectors.toMap(t -> kv.split("\\=", 2)[0], t -> kv.split("\\=", 2)[1]));

        List<Person> list = Arrays.asList(
                new Person(18, 3939),
                new Person(38, 9999),
                new Person(17, 9999),
                new Person(19, 9988),
                new Person(38, 99)
        );
        //取出所有年龄放到list集合中
        List<Integer> toList = list.stream().map(Person::getAge)
                .collect(Collectors.toList());

        //取出所有年龄放到set集合中
        Set<Integer> toSet = list.stream().map(Person::getAge)
                .collect(Collectors.toSet());

        //取出所有年龄放到hashSet集合中
        HashSet<Integer> toHashSet = list.stream().map(Person::getAge)
                .collect(Collectors.toCollection(HashSet::new));

        //获取集合中元素总和
        Long count = list.stream().collect(Collectors.counting());

        //获取年龄平均值
        Double avg = list.stream().collect(Collectors.averagingInt(Person::getAge));

        //获取工资总和
        Double sum = list.stream().collect(Collectors.summingDouble(Person::getSalary));

        //获取工资最大值的人
        Optional<Person> max = list.stream().collect(Collectors.maxBy((p1, p2) -> Double.compare(p1.getSalary(), p2.getSalary())));
        System.out.println(max.get());
        //获取工资最小值的人
        Optional<Person> min = list.stream().collect(Collectors.minBy((p1, p2) -> Double.compare(p1.getSalary(), p2.getSalary())));
        System.out.println(min.get());

        //获取元素个数、总和、最小值、平均值、最大值
        DoubleSummaryStatistics collect1 = list.stream().collect(Collectors.summarizingDouble(Person::getSalary));
        System.out.println(collect1);
        //输出结果:DoubleSummaryStatistics{count=5, sum=34024.000000, min=99.000000, average=6804.800000, max=9999.000000}


        //按年龄分组，年龄相同的是一组
        Map<Integer, List<Person>> 分组 = list.stream().collect(Collectors.groupingBy(Person::getAge));

        //按年龄分组后按工资分组，多级分组
        Map<Integer, ArrayList<Person>> collect2 = list.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Person::getAge))), ArrayList::new)));


        collect2.forEach((k, v) -> System.out.println("k =" + k + "   v =" + v.toString()));

        //把集合用"-"号分割
        String splicingListStr = list.stream().map(t -> String.valueOf(t.getAge())).collect(Collectors.joining("-"));
        System.out.println(splicingListStr);

        List<Long> collect3 = Stream.of(splicingListStr.split("-")).map(t -> Long.valueOf(t)).collect(Collectors.toList());

        System.out.println(collect3);
    }


    private static void matchStream() {
        List<Person> list = Arrays.asList(
                new Person(18, 3939),
                new Person(38, 9999),
                new Person(17, 9999),
                new Person(19, 9988),
                new Person(38, 99)
        );

        //是否匹配所有元素
        boolean b = list.stream().allMatch(t -> t.getAge() == 18);
        System.out.println("是否匹配所有的元素 :" + b); //是否匹配所有的元素 :false

        boolean anyMatch = list.stream().anyMatch(t -> t.getAge() == 18);
        System.out.println("是否匹配一个元素 ：" + anyMatch); //是否匹配一个元素 ：true

        //流中是否没有匹配元素，此处返回false
        boolean noneMatch = list.stream().noneMatch(t -> t.getAge() == 12);
        System.out.println("流中是否没有匹配元素 :" + noneMatch); //流中是否没有匹配元素 :true

        //排序后获取第一个元素
        Optional<Person> first = list.stream().sorted((y, x) -> x.getAge().compareTo(y.getAge())).findFirst();
        System.out.println(first.get().toString()); //Person{age=17, salary=9999.0}

        //返回流中最大值 此处根据年龄比较
        Optional<Person> max = list.stream().max(Comparator.comparing(Person::getAge));
        System.out.println("流中的年龄最大值对象 ：" + max.get().toString()); //流中的年龄最大值对象 ：Person{age=38, salary=9999.0}

        //返回流中的最小值对象，根据年龄比较
        Optional<Person> min = list.stream().min(Comparator.comparing(Person::getAge));
        System.out.println("流中的年龄最大值对象 ：" + min.get().toString()); //流中的年龄最大值对象 ：Person{age=17, salary=9999.0}
        //获取最小年龄
        Optional<Integer> min1 = list.stream().map(Person::getAge).min(Integer::compareTo);
        System.out.println("最小年龄数字是 ：" + min1.get()); //最小年龄数字是 ：17

        //获取最da年龄
        Optional<Integer> max1 = list.stream().map(Person::getAge).max(Integer::compareTo);
        System.out.println("最大年龄数字是 ：" + max1.get()); //最大年龄数字是 ：38



    }

    private static void mapStream() {
        List<String> list = Arrays.asList("a", "vvv", "ddd");

        //中间操作：不会执行任何操作
        Stream<String> stream = list.stream()
                .map(x -> x + "weng");

        //终止操作：一次性执行全部内容，惰性求值
        stream.forEach(System.out::println);
        System.out.println("===================================按照工资排序,降序====================================================");


        List<Person> personList = Arrays.asList(
                new Person(18, 3939),
                new Person(38, 9999),
                new Person(19, 9988),
                new Person(38, 99)
        );
        //按照工资排序,降序
        Stream<Person> sorted = personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed());
        sorted.forEach(System.out::println);
        System.out.println("=====================================按照年龄降序排序，如果年龄相同按收入排序==================================================");
        //按照年龄降序排序，如果年龄相同按收入排序
        Stream<Person> sorted1 = personList.stream().sorted((x, y) -> {
            if (x.getAge().equals(y.getAge())) {
                return x.getSalary() > y.getSalary() ? -1 : 1;
            }
            return y.getAge().compareTo(x.getAge());
        });
        sorted1.forEach(System.out::println);

        //按照年龄进行去重
        ArrayList<Person> collect = personList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Person::getAge))), ArrayList::new));
    }


    public static void createStream() {
        //1、通过Collection集合创建
        List list = new ArrayList<>();
        Stream collectionStream = list.stream();

        //2、通过Arrays中提供的静态方法获取数组流
        String[] strings = new String[10];
        Stream<String> arrayStream = Arrays.stream(strings);

        //3、通过Stream的静态方法获取流
        Stream<String> stream3 = Stream.of("c", "s", "s");

        //4，创建无限流
        //迭代  iterate参数 (从 0 开始 ，每次+2)
        Stream<Integer> stream4_1 = Stream.iterate(0, x -> x + 2);

    }

    public static void filterStream() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9);

        //中间操作：不会执行任何操作
        Stream<Integer> stream = list.stream()
                .filter(e -> {
                    System.out.println("过滤 中间操作");
                    return e > 3;
                })
                // limit(n)  截断流，使其元素不超过给定数量
                //.limit(2)
                //skip(n)  跳过元素，返回一个扔掉了前n个元素的流，如果流中元素不足n个，则返回一个空流。与limit互补
                .skip(2);
        //终止操作：一次性执行全部内容，惰性求值
        //多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何的处理!
        //而在终止操作时一次性全部处理，称为“惰性求值”。
        stream.forEach(System.out::println);


        //去冲,根据对象的hasCode和equals方法，所以如果要对实体类对象执行去重操作
        list.stream().distinct();
    }

}


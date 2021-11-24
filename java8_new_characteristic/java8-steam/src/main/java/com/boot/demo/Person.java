package com.boot.demo;

/**
 * DATE: 2021/9/24 4:20 下午
 * Author: WengChuanJie
 */
public class Person {

        private Integer age;

        private double salary;

        public Person() {
        }

        public Person(Integer age, double salary) {
            this.age = age;
            this.salary = salary;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", salary=" + salary +
                    '}';
        }

}

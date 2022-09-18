package com.weng.demo.builder_model;

/**
 * @DATE: 2022/8/31 4:44 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class User {
    private String name;

    private String password;

    private String label;

    private String img;

    private User() {
    }

    private User(String name, String password, String label, String img) {
        this.name = name;
        this.password = password;
        this.label = label;
        this.img = img;
    }

    public static UserBuilder builder(){
        return new UserBuilder();
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", label='" + label + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public static class UserBuilder {
        private String name;

        private String password;

        private String label;

        private String img;


        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder label(String label) {
            this.label = label;
            return this;
        }

        public UserBuilder img(String img) {
            this.img = img;
            return this;
        }

        public  User build(){
            //做一些校验
            if (null == name || null== password){
                System.out.println("用户名或者密码不能为空");

            }
            return new User(this.name,this.password,this.label,this.img);
        }
    }
}

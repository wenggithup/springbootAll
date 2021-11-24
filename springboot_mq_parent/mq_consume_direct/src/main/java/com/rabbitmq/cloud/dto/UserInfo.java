package com.rabbitmq.cloud.dto;


import java.io.Serializable;
import java.util.List;

public class UserInfo implements Serializable {
    private String username;
    private String password;
    private List<ClassRoom> list;

    public UserInfo(String username, String password, List<ClassRoom> list) {
        this.username = username;
        this.password = password;
        this.list = list;
    }

    public List<ClassRoom> getList() {
        return list;
    }

    public void setList(List<ClassRoom> list) {
        this.list = list;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfo() {
    }
}

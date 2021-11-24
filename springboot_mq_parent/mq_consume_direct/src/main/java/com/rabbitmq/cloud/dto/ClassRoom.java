package com.rabbitmq.cloud.dto;

import java.io.Serializable;

/**
 * DATE: 2021/4/5 9:09 下午
 * Author: WengChuanJie
 */
public class ClassRoom implements Serializable {
    private String className;
    private Integer classId;

    public ClassRoom() {
    }

    public ClassRoom(String className, Integer classId) {
        this.className = className;
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}

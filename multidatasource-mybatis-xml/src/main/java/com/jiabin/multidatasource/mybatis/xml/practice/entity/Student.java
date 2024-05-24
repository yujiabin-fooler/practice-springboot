package com.jiabin.multidatasource.mybatis.xml.practice.entity;

import com.jiabin.multidatasource.mybatis.xml.practice.constant.GenderEnum;

/**
 * Student
 *
 */
public class Student {

    private Integer id;

    private String name;

    private Integer age;

    private GenderEnum gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }
}
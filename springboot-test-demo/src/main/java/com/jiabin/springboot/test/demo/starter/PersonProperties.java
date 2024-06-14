package com.jiabin.springboot.test.demo.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@SuppressWarnings("serial")
@ConfigurationProperties(prefix = "com.person")
public class PersonProperties implements Serializable {
    private String name;
    private int age;
    private String sex = "M";

    public PersonProperties() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
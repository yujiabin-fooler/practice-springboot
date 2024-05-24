package com.jiabin.properties.practice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * UserConfig
 *
 **/
@Configuration
@ConfigurationProperties(prefix = "demo.user")
@PropertySource(value = "classpath:user.yml", encoding = "utf-8")
public class UserConfig {

    @Value("${name}")
    private String name;

    @Value("${age}")
    private int age;

    @Value("${desc}")
    private String desc;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

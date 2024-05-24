package com.jiabin.file.dowload.practice.entity;

public class FileInfo {
    private String name;
    private String token; // 添加Token属性

    public FileInfo(String name, String token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }
}
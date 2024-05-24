package com.jiabin.minio.practice.dto;

/**
 * 文件上传返回结果
 */
public class MinioUploadDto {
    private String url;
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

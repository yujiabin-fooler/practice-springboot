package com.jiabin.nginx.file.download.practice.entity;

import lombok.Data;

@Data
public class FileObject {
    private String fileName;
    private String md5;
    private String expires;
    private String downloadUrl;
}
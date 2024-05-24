package com.jiabin.email.practice.entity;

import lombok.Data;

@Data
public class Attachment {

    private String name;

    private String contentId;

    private byte[] data;

    private String contentType;


}
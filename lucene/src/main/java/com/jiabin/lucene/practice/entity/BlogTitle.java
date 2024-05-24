package com.jiabin.lucene.practice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("blog_title")
public class BlogTitle implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String title;

    private String description;
}

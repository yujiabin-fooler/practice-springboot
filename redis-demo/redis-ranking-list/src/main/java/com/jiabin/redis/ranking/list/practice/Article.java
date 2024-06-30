package com.jiabin.redis.ranking.list.practice;

public class Article {
    private String id;
    private String title;
    private int likeCount;
    // 省略构造函数、getter和setter方法


    public Article() {

    }

    public Article(String id, String title, int likeCount) {
        this.id = id;
        this.title = title;
        this.likeCount = likeCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}

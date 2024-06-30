package com.jiabin.redis.social.activities.practice;

import java.time.Instant;

public class StatusUpdate {
    private String userId;
    private String content;
    private Instant timestamp;
    // 省略构造函数、getter和setter方法


    public StatusUpdate(String userId, String content, Instant timestamp) {
        this.userId = userId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}

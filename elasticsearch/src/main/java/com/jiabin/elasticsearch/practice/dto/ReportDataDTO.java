package com.jiabin.elasticsearch.practice.dto;


public class ReportDataDTO {

    private long timestamp;

    private long views;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }
}

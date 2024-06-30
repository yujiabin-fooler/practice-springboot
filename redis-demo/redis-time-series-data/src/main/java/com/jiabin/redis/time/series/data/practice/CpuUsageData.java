package com.jiabin.redis.time.series.data.practice;

import java.time.Instant;

public class CpuUsageData {
    private Instant timestamp;
    private double cpuUsage;


    // 省略构造函数、getter和 setter 方法
    public CpuUsageData(Instant timestamp, double cpuUsage) {
        this.timestamp = timestamp;
        this.cpuUsage = cpuUsage;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }


    
}

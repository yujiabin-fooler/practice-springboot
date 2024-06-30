package com.jiabin.redis.task.scheduling.practice;

import java.time.LocalDateTime;

public class SampleTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Task is running: " + LocalDateTime.now());
        // 执行任务逻辑
    }
}

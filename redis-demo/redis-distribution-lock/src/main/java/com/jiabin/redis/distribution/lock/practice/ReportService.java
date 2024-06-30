package com.jiabin.redis.distribution.lock.practice;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ReportService {

    @Autowired
    private RedissonClient redissonClient;

    public void generateDailyReport() {
        RLock lock = redissonClient.getLock("dailyReportLock");
        try {
            // 尝试获取锁，最多等待3秒，锁的自动过期时间设置为10秒
            if (lock.tryLock(3, 10, TimeUnit.SECONDS)) {
                // 执行生成日报表的操作
                System.out.println("Generating daily report...");
                // 模拟长时间运行的任务
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Daily report generated.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }
}

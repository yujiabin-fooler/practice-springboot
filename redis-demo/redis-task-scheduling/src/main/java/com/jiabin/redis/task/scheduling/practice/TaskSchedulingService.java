package com.jiabin.redis.task.scheduling.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TaskSchedulingService {

    @Autowired
    private RedisTemplate<String, Runnable> redisTemplate;

    public void scheduleTask(Runnable task, long delay, TimeUnit timeUnit) {
        // 将任务和延迟时间存储到Redis中
        redisTemplate.opsForValue().set(
                "task:" + task.hashCode(),
                task,
                timeUnit.toSeconds(delay),
                timeUnit
        );
        // 使用schedule命令安排任务在未来执行
        String scheduleCommand = String.format(
                "SCHEDULE %d %s",
                System.currentTimeMillis() + timeUnit.toMillis(delay),
                "task:" + task.hashCode()
        );
        redisTemplate.execute((RedisConnection connection) -> {
//            connection.schedule(scheduleCommand);
            return null;
        });
    }
}

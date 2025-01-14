package com.jiabin.schedule.quartz.group.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: Quartz 定时任务集群模式应用启动类2
 * @Author jiabin.yu
 * @Date: 2020/11/18
 */
@SpringBootApplication
public class DemoScheduleQuartzGroupApplication2 {

    public static void main(String[] args) {
        System.setProperty("server.port", "8552");
        SpringApplication.run(DemoScheduleQuartzGroupApplication2.class);
    }
}

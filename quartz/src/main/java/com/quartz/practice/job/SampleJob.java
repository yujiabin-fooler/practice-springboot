package com.quartz.practice.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SampleJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
    	if (Thread.currentThread().isInterrupted()) {
            // 响应中断请求
            // 可以执行清理操作并退出任务
            return;
        }
		 // 在这里编写定时任务的具体逻辑
	    System.out.println("定时任务执行：" + new Date());
    }

}
package com.jiabin.flink.practice;

import com.jiabin.flink.practice.service.FlinkJobService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FlinkPracticeApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(FlinkPracticeApplication.class, args);

		// 获取 FlinkJobService Bean，并运行 Flink 作业
		FlinkJobService flinkJobService = context.getBean(FlinkJobService.class);
		try {
			flinkJobService.runFlinkJob();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}

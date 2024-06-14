package com.jiabin.atomikos.practice;

import com.jiabin.atomikos.practice.config.DBConfig1;
import com.jiabin.atomikos.practice.config.DBConfig2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"com.jiabin.atomikos.practice",/*"com.am.ammq",*/})
@EnableScheduling
@EnableConfigurationProperties(value = {DBConfig1.class, DBConfig2.class})
public class AtomikosPracticeApplication {


	public static void main(String[] args) {
		SpringApplication.run(AtomikosPracticeApplication.class, args);
	}
}

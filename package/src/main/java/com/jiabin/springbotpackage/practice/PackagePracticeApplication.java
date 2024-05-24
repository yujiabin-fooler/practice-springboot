package com.jiabin.springbotpackage.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class PackagePracticeApplication
{
	private static final Logger logger = LoggerFactory.getLogger(PackagePracticeApplication.class);
    public static void main( String[] args )
    {
    	// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		SpringApplication.run(PackagePracticeApplication.class, args);
		logger.info("程序启动成功!");
    }
}

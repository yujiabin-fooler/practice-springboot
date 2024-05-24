package com.jiabin.exceptionhandler.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 
* @Title: App
* @Description:
* 主程序入口 
* @Version:1.0.0
* @date 2018年1月9日
 */

@SpringBootApplication
public class ExceptionHandlerPracticeApplication
{
    public static void main( String[] args )
    {
		SpringApplication.run(ExceptionHandlerPracticeApplication.class, args);
		System.out.println("程序正在运行...");
    }
}

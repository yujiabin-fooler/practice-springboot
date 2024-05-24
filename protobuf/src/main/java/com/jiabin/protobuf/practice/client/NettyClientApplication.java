package com.jiabin.protobuf.practice.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
* @Title: NettyClientApplication
* @Description: Netty 客户端主程序
* @Version:1.0.0
*/
@SpringBootApplication
public class NettyClientApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		ApplicationContext context = SpringApplication.run(NettyClientApplication.class, args);
		NettyClient nettyClient = context.getBean(NettyClient.class);
		nettyClient.run();
	}

}

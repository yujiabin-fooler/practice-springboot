package com.jiabin.protobuf.practice.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
* @Title: NettyServerApplication
* @Description: Netty 服务端主程序
* @Version:1.0.0
*/
@SpringBootApplication
public class NettyServerApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		ApplicationContext context = SpringApplication.run(NettyServerApplication.class, args);
		NettyServer nettyServer = context.getBean(NettyServer.class);
		nettyServer.run();
	}

}

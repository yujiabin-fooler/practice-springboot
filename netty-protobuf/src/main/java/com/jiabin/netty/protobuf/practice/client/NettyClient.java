package com.jiabin.netty.protobuf.practice.client;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 
 * @Title: NettyClient
 * @Description: Netty客户端 心跳测试
 * @Version:1.0.0
 * @author pancm
 * @date 2017年10月8日
 */
@Service("nettyClient")
public class NettyClient {

	private String host = "127.0.0.1"; // ip地址
	private int port = 9876; // 端口
	// 通过nio方式来接收连接和处理连接
	private EventLoopGroup group = new NioEventLoopGroup();
	
	@Autowired
	private NettyClientFilter nettyClientFilter;
	
	/**唯一标记 */
	private boolean initFalg=true;

	/**
	 * Netty创建全部都是实现自AbstractBootstrap。 客户端的是Bootstrap，服务端的则是 ServerBootstrap。
	 **/
	public void run() {
		doConnect(new Bootstrap(), group);
	}

	/**
	 * 重连
	 */
	public void doConnect(Bootstrap bootstrap, EventLoopGroup eventLoopGroup) {
		ChannelFuture f = null;
		try {
			if (bootstrap != null) {
				bootstrap.group(eventLoopGroup);
				bootstrap.channel(NioSocketChannel.class);
				bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
				bootstrap.handler(nettyClientFilter);
				bootstrap.remoteAddress(host, port);
				f = bootstrap.connect().addListener((ChannelFuture futureListener) -> {
					final EventLoop eventLoop = futureListener.channel().eventLoop();
					if (!futureListener.isSuccess()) {
						System.out.println("与服务端断开连接!在10s之后准备尝试重连!");
						eventLoop.schedule(() -> doConnect(new Bootstrap(), eventLoop), 10, TimeUnit.SECONDS);
					}
				});
				if(initFalg){
					System.out.println("Netty客户端启动成功!");
					initFalg=false;
				}
				// 阻塞
				f.channel().closeFuture().sync();
			}
		} catch (Exception e) {
			System.out.println("客户端连接失败!"+e.getMessage());
		}

	}
}

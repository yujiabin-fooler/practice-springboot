package com.jiabin.wechat.mp.practice;

import com.jiabin.wechat.mp.practice.listener.SessionListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.jiabin.wechat.mp.practice.mapper")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	
	@Bean
    public ServletListenerRegistrationBean<SessionListener> sessionListener() {
        ServletListenerRegistrationBean<SessionListener> listenerBean =
                new ServletListenerRegistrationBean<>();
        listenerBean.setListener(new SessionListener());
        return listenerBean;
    }
}

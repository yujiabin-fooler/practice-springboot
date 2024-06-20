package com.jiabin.websocket.spring.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author junqiang.lu
 */
@SpringBootApplication(scanBasePackages = {"com.jiabin.websocket.spring.practice"})
public class DemoWebsocketSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoWebsocketSpringApplication.class, args);
    }

}

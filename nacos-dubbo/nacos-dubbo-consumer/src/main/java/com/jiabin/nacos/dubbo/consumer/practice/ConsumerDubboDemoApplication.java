package com.jiabin.nacos.dubbo.consumer.practice;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@EnableDubbo
@PropertySource(value = "classpath:/consumer-config.properties")
@SpringBootApplication
public class ConsumerDubboDemoApplication {

    @Reference(version = "${demo.service.version}")
    private DubboDemoService demoService;

    @PostConstruct
    public void init() {
        String a=  demoService.helloDubbo();
        System.out.println(a);
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerDubboDemoApplication.class, args);

    }

}

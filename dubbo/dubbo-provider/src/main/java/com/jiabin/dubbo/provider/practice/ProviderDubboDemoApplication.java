package com.jiabin.dubbo.provider.practice;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;



@PropertySource(value = "classpath:/provider-config.properties")
@EnableDubbo(scanBasePackages = "com.jiabin.dubbo.provider.practice.dubbo")
@SpringBootApplication
public class ProviderDubboDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(ProviderDubboDemoApplication.class, args);
        System.out.println(app);
    }

}

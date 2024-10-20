package com.jiabin.practice.dubbo.servide.register.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 生产者
 *
 * @author jiabin.yu
 * @date 2024/10/5 22:46
 */
@SpringBootApplication
@EnableDubbo
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

}

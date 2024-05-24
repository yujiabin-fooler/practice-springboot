package com.jiabin.nacos.provider.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ProviderCloudDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderCloudDemoApplication.class, args);
    }

}

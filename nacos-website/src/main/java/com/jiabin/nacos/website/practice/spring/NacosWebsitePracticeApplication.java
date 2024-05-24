package com.jiabin.nacos.website.practice.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// Demo for dynamic Page by nacos
@SpringBootApplication
public class NacosWebsitePracticeApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppConfiguration.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(NacosWebsitePracticeApplication.class, args);
    }
}

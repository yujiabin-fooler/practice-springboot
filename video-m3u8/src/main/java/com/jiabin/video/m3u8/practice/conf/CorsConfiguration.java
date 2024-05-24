package com.jiabin.video.m3u8.practice.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/play/**")
                        .allowedOrigins("*") // 允许所有来源访问
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(false)
                        .maxAge(3600);
            }
        };
    }
}
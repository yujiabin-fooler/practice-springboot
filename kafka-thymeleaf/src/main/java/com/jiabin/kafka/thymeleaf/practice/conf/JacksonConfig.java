package com.jiabin.kafka.thymeleaf.practice.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {
	 @Bean
	    public ObjectMapper objectMapper() {
	        return Jackson2ObjectMapperBuilder.json()
	                .modules(new JavaTimeModule()) // 启用对java.time包的支持
	                .build();
	    }
}

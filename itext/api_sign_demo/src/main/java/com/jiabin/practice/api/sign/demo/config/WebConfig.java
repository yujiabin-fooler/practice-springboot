package com.jiabin.practice.api.sign.demo.config;

import com.jiabin.practice.api.sign.demo.interceptor.SignInterceptor;
import com.jiabin.practice.api.sign.demo.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    AppService appService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SignInterceptor(appService))
                //只拦截需要接口验签的请求
                .addPathPatterns("/app/**");
    }
}

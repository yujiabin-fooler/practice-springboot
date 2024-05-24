package com.jiabin.dubbo.nacos.xml.practice.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * {@link com.jiabin.dubbo.nacos.xml.practice.service.DemoService} provider demo XML bootstrap
 */
public class DemoServiceProviderXmlBootstrap {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        context.setConfigLocation("/META-INF/spring/dubbo-provider-context.xml");
        context.refresh();
        System.out.println("DemoService provider (XML) is starting...");
        System.in.read();
    }
}
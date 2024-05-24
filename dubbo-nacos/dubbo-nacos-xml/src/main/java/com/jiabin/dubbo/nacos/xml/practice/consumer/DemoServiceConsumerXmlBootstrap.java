package com.jiabin.dubbo.nacos.xml.practice.consumer;

import com.jiabin.dubbo.nacos.xml.practice.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * {@link com.jiabin.dubbo.nacos.xml.practice.service.DemoService} consumer demo XML bootstrap
 */
public class DemoServiceConsumerXmlBootstrap {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        context.setConfigLocation("/META-INF/spring/dubbo-consumer-context.xml");
        context.refresh();
        System.out.println("DemoService consumer (XML) is starting...");
        DemoService demoService = context.getBean("demoService", DemoService.class);
        for (int i = 0; i < 10; i++) {
            System.out.println(demoService.sayName("Nacos"));
        }
        context.close();
    }
}
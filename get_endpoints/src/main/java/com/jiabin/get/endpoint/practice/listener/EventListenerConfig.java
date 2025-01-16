package com.jiabin.get.endpoint.practice.listener;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class EventListenerConfig {

  @EventListener
  public void handleContextRefresh(ContextRefreshedEvent event) {
      ApplicationContext applicationContext = event.getApplicationContext();
      RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext
          .getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
      Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping
          .getHandlerMethods();
      map.forEach((key, value) -> System.err.printf("%s, %s%n", key, value)) ;
  }
  
}

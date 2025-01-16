package com.jiabin.get.endpoint.practice.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

@Component
public class ServletRequestEventListener implements ApplicationListener<ServletRequestHandledEvent> {

  @Override
  public void onApplicationEvent(ServletRequestHandledEvent event) {
    // TODO
    System.err.println(event.getDescription()) ;
  }

}

package com.jiabin.value.fresh.practice.event;

import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class PackValueChangeEvent extends ApplicationEvent {

  public PackValueChangeEvent(Object source) {
    super(source) ;
  }
}

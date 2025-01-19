package com.jiabin.transaction.hook.practice.event;

import org.springframework.context.ApplicationEvent;

public class BookCreationEvent extends ApplicationEvent {

  private static final long serialVersionUID = 1L;

  public BookCreationEvent(Object source) {
    super(source);
  }
}

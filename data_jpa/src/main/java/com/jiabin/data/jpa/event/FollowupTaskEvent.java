package com.jiabin.data.jpa.event;

import org.springframework.context.ApplicationEvent;

public class FollowupTaskEvent extends ApplicationEvent {
  
  private static final long serialVersionUID = 1L ;

  public FollowupTaskEvent(Object source) {
    super(source) ;
  }
}

package com.jiabin.redis.message.practice.redismessage.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jiabin.redis.message.practice.redismessage.domain.User;

@Component
public class MessageReceiver {

  private final Logger logger = LoggerFactory.getLogger(getClass()) ;
  
//  public void receiveMessage(String message) {
//    logger.info("接收到消息: {}", message) ;
//  }
  
//  public void receiveMessage(String message, String channel) {
//    logger.info("从频道: {}, 接收到消息: {}", channel, message) ;
//  }
  
  public void receiveMessage(User user, String channel) {
//    System.out.println(1 / 0) ;
    logger.info("从频道: {}, 接收到消息: {}", channel, user) ;
  }
}

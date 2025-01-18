package com.jiabin.redis.message.practice.redismessage.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.ErrorHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiabin.redis.message.practice.redismessage.domain.User;
import com.jiabin.redis.message.practice.redismessage.receiver.MessageReceiver;

@Configuration
public class RedisConfig {
  
  private final Logger logger = LoggerFactory.getLogger(MessageReceiver.class) ;
  
  @Bean
  RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
      MessageListenerAdapter listenerAdapter) {

    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.addMessageListener(listenerAdapter, new PatternTopic("chat_*"));
//    container.addMessageListener(listenerAdapter, ChannelTopic.of("chat")) ;

    // 默认使用的线程池对象是：SimpleAsyncTaskExecutor，线程名称的前缀是当前beanName进行拼接（如这里beanName=container）
    SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor() ;
    taskExecutor.setThreadNamePrefix("vm-chat-") ;
    taskExecutor.setVirtualThreads(true) ;
//    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor() ;
//    taskExecutor.setCorePoolSize(5) ;
//    taskExecutor.setMaxPoolSize(5) ;
//    taskExecutor.setQueueCapacity(1000) ;
//    taskExecutor.initialize() ;
//    taskExecutor.setThreadNamePrefix("pack-chat-") ;
    container.setTaskExecutor(taskExecutor) ;
    container.setErrorHandler(new ErrorHandler() {
      public void handleError(Throwable t) {
        logger.error("处理消息发生异常: {}", t.getMessage()) ;
      }
    });
    
    return container;
  }

//  @Bean
//  MessageListenerAdapter listenerAdapter(MessageReceiver receiver) {
//    MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(receiver, "receiveMessage");
//    return listenerAdapter ;
//  }
  
  @Bean
  MessageListenerAdapter listenerAdapter(MessageReceiver receiver, ObjectMapper objectMapper) {
    MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(receiver, "receiveMessage");
    listenerAdapter.setSerializer(new RedisSerializer<User>() {
      public byte[] serialize(User value) throws SerializationException {
        try {
          return objectMapper.writeValueAsBytes(value)  ;
        } catch (JsonProcessingException e) {
          throw new RuntimeException(e) ;
        }
      }
      public User deserialize(byte[] bytes) throws SerializationException {
        try {
          return objectMapper.readValue(bytes, User.class) ;
        } catch (IOException e) {
          throw new RuntimeException(e) ;
        }
      }
    }) ;
    return listenerAdapter ;
  }
}

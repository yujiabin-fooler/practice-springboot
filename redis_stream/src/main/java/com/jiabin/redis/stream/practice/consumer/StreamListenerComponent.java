package com.jiabin.redis.stream.practice.consumer;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.StreamMessageListenerContainer.StreamMessageListenerContainerOptions;

@Configuration
public class StreamListenerComponent {

  private final RedisConnectionFactory connectionFactory ;
  private final OperatorLogStreamListener streamListener ;
  
  public StreamListenerComponent(RedisConnectionFactory connectionFactory, OperatorLogStreamListener streamListener) {
    this.connectionFactory = connectionFactory;
    this.streamListener = streamListener;
  }

  @Bean
  StreamMessageListenerContainer<String, MapRecord<String, String, String>> listener() {
    StreamMessageListenerContainerOptions<String, MapRecord<String, String, String>> containerOptions = StreamMessageListenerContainerOptions
        .builder().pollTimeout(Duration.ofMillis(100)).build();

    StreamMessageListenerContainer<String, MapRecord<String, String, String>> container = StreamMessageListenerContainer
        .create(connectionFactory, containerOptions) ;
    Consumer consumer = Consumer.from("optgroup", "packconsumer") ;
    container.receive(consumer, StreamOffset.create("sys:operator", ReadOffset.lastConsumed()), streamListener) ;
    container.start() ; 
    return container ;
  }
}

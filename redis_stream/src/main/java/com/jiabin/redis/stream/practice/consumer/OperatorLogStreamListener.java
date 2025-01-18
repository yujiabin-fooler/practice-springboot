package com.jiabin.redis.stream.practice.consumer;

import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class OperatorLogStreamListener implements StreamListener<String, MapRecord<String, String, String>> {

  private final StringRedisTemplate stringRedisTemplate ;
  public OperatorLogStreamListener(StringRedisTemplate stringRedisTemplate) {
    this.stringRedisTemplate = stringRedisTemplate ;
  }
  
	@Override
	public void onMessage(MapRecord<String, String, String> message) {
		System.err.printf("Id: %s, Stream: %s, Body: %s%n", message.getId(), message.getStream(), message.getValue()) ;
		this.stringRedisTemplate.opsForStream().acknowledge("optgroup", message) ;
	} 
}
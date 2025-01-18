package com.jiabin.redis.stream.practice.consumer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.connection.stream.StreamReadOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class ConsumeComponent {

  private final StringRedisTemplate stringRedisTemplate ;
  public ConsumeComponent(StringRedisTemplate stringRedisTemplate) {
    this.stringRedisTemplate = stringRedisTemplate ;
  }
  
  @PostConstruct
  public void init() {
//    consume() ;
  }
  
  @SuppressWarnings("unchecked")
  public void consume() {
    new Thread(() -> {
      while (true) {
        Consumer consumer = Consumer.from("optgroup", "packconsumer");
        StreamReadOptions options = StreamReadOptions.empty().count(2);
        StreamOffset<String> streamOffset = StreamOffset.create("sys:operator", ReadOffset.lastConsumed());
        List<MapRecord<String, Object, Object>> records = this.stringRedisTemplate.opsForStream().read(consumer, options, streamOffset) ;
        records.forEach(rec -> {
          RecordId id = rec.getId() ;
          Map<Object,Object> value = rec.getValue() ;
          System.err.printf("id: %s, 内容: %s%n", id.getValue(), value) ;
        });
        try {
          TimeUnit.MILLISECONDS.sleep(300) ;
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start() ;
  }
  
}

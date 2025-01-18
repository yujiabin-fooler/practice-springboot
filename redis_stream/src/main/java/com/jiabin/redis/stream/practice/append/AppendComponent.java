package com.jiabin.redis.stream.practice.append;

import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.jiabin.redis.stream.practice.domain.OperatorLog;

@Component
public class AppendComponent {

  private final StringRedisTemplate stringRedisTemplate ;
  public AppendComponent(StringRedisTemplate stringRedisTemplate) {
    this.stringRedisTemplate = stringRedisTemplate ;
  }
  
  public RecordId add(OperatorLog log) {
    ObjectRecord<String, OperatorLog> record = StreamRecords.objectBacked(log).withStreamKey("sys:operator") ;
    RecordId recordId = this.stringRedisTemplate.opsForStream().add(record) ;
    return recordId ;
  }
}

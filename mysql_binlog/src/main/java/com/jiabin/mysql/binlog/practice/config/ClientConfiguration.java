package com.jiabin.mysql.binlog.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;

@Configuration
public class ClientConfiguration {

  @Bean
  BinaryLogClient client() {
    BinaryLogClient client = new BinaryLogClient("118.24.111.33", 3307, "test", "root", "123123");
    EventDeserializer eventDeserializer = new EventDeserializer();
    eventDeserializer.setCompatibilityMode(
        EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
        EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
    );
    client.setEventDeserializer(eventDeserializer) ;
    return client ;
  }
}

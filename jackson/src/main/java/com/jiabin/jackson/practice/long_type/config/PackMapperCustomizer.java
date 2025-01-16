package com.jiabin.jackson.practice.long_type.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

//@Component
public class PackMapperCustomizer implements Jackson2ObjectMapperBuilderCustomizer {

  @Override
  public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
    jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance) ;
  }
}

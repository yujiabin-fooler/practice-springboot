package com.jiabin.type.conversion.practice.convert;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jiabin.type.conversion.practice.domain.User;

@Component
@ConfigurationPropertiesBinding
public class StringToUserConverter implements Converter<String, User> {

  @Override
  public User convert(String source) {
    if (StringUtils.hasLength(source)) {
      String[] args = source.split(",") ;
      return new User(args[1], Integer.valueOf(args[0])) ;
    }
    return null ;
  }
}

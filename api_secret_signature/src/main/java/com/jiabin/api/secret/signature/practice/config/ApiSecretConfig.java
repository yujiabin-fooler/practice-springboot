package com.jiabin.api.secret.signature.practice.config;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StreamUtils;

import com.jiabin.api.secret.signature.practice.crypto.AESCryptResolver;
import com.jiabin.api.secret.signature.practice.crypto.CryptResolver;
import com.jiabin.api.secret.signature.practice.crypto.MD5SignatureResolver;
import com.jiabin.api.secret.signature.practice.crypto.SignatureResolver;

@Configuration
public class ApiSecretConfig {

  @Bean
  CryptResolver cryptResolver() {
    return new AESCryptResolver() ;
  }
  
  @Bean
  SignatureResolver signatureResolver() {
    return new MD5SignatureResolver() ;
  }
  
  /**
   * jackson 输出字符串时，会自带双引号
   * @param builder
   * @return
   */
  @Bean
  MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(Jackson2ObjectMapperBuilder builder) {
    return new MappingJackson2HttpMessageConverter(builder.build()) {
      @Override
      protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage)
          throws IOException, HttpMessageNotWritableException {
        if (object instanceof String body) {
          StreamUtils.copy(body, StandardCharsets.UTF_8, outputMessage.getBody());
          return ;
        }
        super.writeInternal(object, type, outputMessage) ;
      }
    };
  }
}

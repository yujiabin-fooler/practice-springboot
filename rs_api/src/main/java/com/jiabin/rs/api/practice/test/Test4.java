package com.jiabin.rs.api.practice.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.jiabin.rs.api.practice.conversion.JSONToObjectReader;
import com.jiabin.rs.api.practice.domain.User;
import com.jiabin.rs.api.practice.exception.PackExceptionMapper;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.InvocationCallback;
import jakarta.ws.rs.core.MediaType;

public class Test4 {

  public static void main(String[] args) {
    ClientBuilder builder = ClientBuilder.newBuilder() ;
    builder.connectTimeout(1000, TimeUnit.MILLISECONDS) ;
    builder.readTimeout(1000, TimeUnit.MILLISECONDS) ;
    builder.register(PackExceptionMapper.class) ;
    Client client = builder.build() ;
//    Response response = client.target("http://localhost:9100/users")
//        .register(JSONToObjectReader.class)
//        .request(MediaType.APPLICATION_JSON)
//        .get() ;
//    User ret = response.readEntity(User.class) ;
//    System.out.println(ret) ;
    
    client.target("http://localhost:9100/users")
        .register(JSONToObjectReader.class)
        .request(MediaType.APPLICATION_JSON)
        .buildGet()
        .submit(new InvocationCallback<List<User>>() {
          @Override
          public void completed(List<User> response) {
            System.out.printf("返回结果: %s%n", response) ;
          }
          @Override
          public void failed(Throwable throwable) {
            System.err.printf("发生错误: %s%n", throwable.getMessage()) ;
          }
        }) ;
        
  }
}

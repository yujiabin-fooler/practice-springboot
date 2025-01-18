package com.jiabin.rs.api.practice.test;

import java.util.concurrent.CompletionStage;

import com.jiabin.rs.api.practice.conversion.JSONToObjectReader;
import com.jiabin.rs.api.practice.domain.User;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

public class Test5 {

  public static void main(String[] args) {
    Client client = ClientBuilder.newClient() ;// .register(LoggingFilter.class) ;
    
    CompletionStage<User> stage = client.target("http://localhost:9100/users/666")
        .register(JSONToObjectReader.class)
        .request(MediaType.APPLICATION_JSON)
        .rx()
        .get(User.class) ;
    stage.whenComplete((res, ex) -> {
      if (ex != null) {
        System.err.printf("发生错误: %s%n", ex.getMessage()) ;
      } else {
        System.out.println(res) ;
      }
    }) ;
  }
}

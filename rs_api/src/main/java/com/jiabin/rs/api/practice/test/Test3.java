package com.jiabin.rs.api.practice.test;

import java.util.Map;

import com.jiabin.rs.api.practice.conversion.JSONToObjectReader;
import com.jiabin.rs.api.practice.domain.User;
import com.jiabin.rs.api.practice.exception.PackExceptionMapper;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class Test3 {

  public static void main(String[] args) {
    Client client = ClientBuilder.newClient() ;
    Response response = client.target("http://localhost:9100/users/exception")
        .register(PackExceptionMapper.class)
        .register(JSONToObjectReader.class)
        .request(MediaType.APPLICATION_JSON)
        .get() ;
    int status = response.getStatus() ;
    if (status >= 400) {
      System.err.printf("请求错误: %s%n", response.readEntity(Map.class)) ;
    } else {
      User ret = response.readEntity(User.class) ;
      System.out.println(ret) ;
    }
  }
}

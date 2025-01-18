package com.jiabin.rs.api.practice.test;

import com.jiabin.rs.api.practice.conversion.JSONToObjectReader;
import com.jiabin.rs.api.practice.domain.User;
import com.jiabin.rs.api.practice.filter.AuthFilter;
import com.jiabin.rs.api.practice.filter.LoggingFilter;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class Test1 {

  public static void main(String[] args) {
    Client client = ClientBuilder.newClient() ;// .register(LoggingFilter.class) ;
//    Response response = client.target("http://localhost:9100/users/666")
//      .request(MediaType.APPLICATION_JSON)
//      .get() ;
//    System.out.println(response.readEntity(String.class)) ;
    
    Response response = client.target("http://localhost:9100/users/666")
        .register(JSONToObjectReader.class)
        .request(MediaType.APPLICATION_JSON)
        .get() ;
    Object ret = response.readEntity(User.class) ;
    System.out.println(ret) ;
    
    response = client.target("http://localhost:9100/users/header")
        .register(LoggingFilter.class)
        .register(AuthFilter.class)
        .request(MediaType.TEXT_PLAIN)
        .header("x-token", "6666666666")
        .get() ;
    ret = response.readEntity(String.class) ;
    System.out.println(ret) ;
  }
}

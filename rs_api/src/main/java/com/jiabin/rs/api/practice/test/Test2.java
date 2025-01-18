package com.jiabin.rs.api.practice.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.jiabin.rs.api.practice.conversion.JSONToObjectReader;
import com.jiabin.rs.api.practice.domain.User;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.InvocationCallback;
import jakarta.ws.rs.core.MediaType;

public class Test2 {

  public static void main(String[] args) throws Exception {
    int core = Runtime.getRuntime().availableProcessors() ;
    ExecutorService executorService = new ThreadPoolExecutor(core, core, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100)) ;
    ClientBuilder builder = ClientBuilder.newBuilder().executorService(executorService) ;
    Client client = builder.build().register(JSONToObjectReader.class) ;
    
    Future<User> future = client.target("http://localhost:9100/users/666")
        .request(MediaType.APPLICATION_JSON)
        .async()
        .get(new InvocationCallback<User>() {
          @Override
          public void completed(User response) {
            System.out.printf("%s - 请求完成: %s%n", Thread.currentThread().getName(), response) ;
          }
          @Override
          public void failed(Throwable throwable) {
            System.err.printf("请求失败: %s%n", throwable.getMessage()) ;
          }
        }) ;
    User ret = future.get() ;
    System.out.printf("返回结果: %s%n", ret) ;
  }
}

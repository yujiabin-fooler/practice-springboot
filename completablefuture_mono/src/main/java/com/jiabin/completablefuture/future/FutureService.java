package com.jiabin.completablefuture.future;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class FutureService {
  
  private ThreadPoolExecutor executor = new ThreadPoolExecutor(200, 200, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000)) ;

  private final RestClient restClient ;
  public FutureService(RestClient restClient) {
    this.restClient = restClient ;
  }
  
  public Map<String, String> query() {
    CompletableFuture<String> scoreFuture = CompletableFuture
        .supplyAsync(() -> this.restClient.get()
            .uri("/users/6666")
            .retrieve()
            .body(String.class), executor) ;
    
    CompletableFuture<String> stockFuture = CompletableFuture
        .supplyAsync(() -> this.restClient.get()
            .uri("/stocks/8888")
            .retrieve()
            .body(String.class), executor) ;
    
    CompletableFuture<String> couponFuture = CompletableFuture
        .supplyAsync(() -> this.restClient.get()
            .uri("/coupons/6666")
            .retrieve()
            .body(String.class), executor) ;
    CompletableFuture.allOf(scoreFuture, stockFuture, couponFuture).join() ;
    
    try {
      return Map.of(
          "score", scoreFuture.get(), 
          "stock", stockFuture.get(), 
          "coupon", couponFuture.get()
        ) ;
    } catch (Exception e) {
      throw new RuntimeException(e) ;
    }
  }
}

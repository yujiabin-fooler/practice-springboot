package com.jiabin.completablefuture.mono;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class MonoService {
  
  private final WebClient webClient ;
  public MonoService(WebClient webClient) {
    this.webClient = webClient ;
  }
  
  public Mono<Map<String, String>> query() {
    StopWatch sw = new StopWatch("PackRequest") ;
    sw.start("三方接口调用") ;
    Mono<String> scoreMono = this.webClient.get().uri("/users/6666").retrieve().bodyToMono(String.class) ;
    Mono<String> stockMono = this.webClient.get().uri("/stocks/8888").retrieve().bodyToMono(String.class) ;
    Mono<String> couponMono = this.webClient.get().uri("/coupons/6666").retrieve().bodyToMono(String.class) ;
    Mono<Map<String, String>> result = Mono.zip(scoreMono, stockMono, couponMono).map(tuple -> Map.of(
          "score", tuple.getT1(), 
          "stock", tuple.getT2(), 
          "coupon", tuple.getT3()
        ));
    sw.stop() ;
    System.out.println(sw.prettyPrint(TimeUnit.MILLISECONDS)) ;
    return result ;
  }
}

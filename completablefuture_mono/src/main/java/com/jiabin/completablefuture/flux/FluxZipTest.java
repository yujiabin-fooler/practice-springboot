package com.jiabin.completablefuture.flux;

import java.util.concurrent.TimeUnit;

import reactor.core.publisher.Flux;

public class FluxZipTest {

  private static int a = 0 ;
  private static int b = 64 ;
  
  public static void main(String[] args) throws Exception {
    
    Flux<Integer> p1 = Flux.generate(sink -> {
      if (a++ >= 5) {
        sink.complete() ;
      } else {
        sink.next(a) ;
        try {
          TimeUnit.MILLISECONDS.sleep(1000) ;
        } catch (InterruptedException e) {}
      }
    }) ;
    // Flux<Integer> p1 = Flux.just(1, 2, 3, 4, 5) ;
    Flux<Character> p2 = Flux.generate(sink -> {
      if (b++ >= 69) {
        sink.complete() ;
      } else {
        sink.next((char) b) ;
        try {
          TimeUnit.MILLISECONDS.sleep(500) ;
        } catch (InterruptedException e) {}
      }
    }) ;
    
//    Flux<Integer> p3 = Flux.just(1, 2, 3, 4, 5) ;
    
    Flux.zip(p1, p2).subscribe(System.out::println) ;
//    Flux.zip(p1, p2).map(tuple -> {
//      return tuple.getT1() + "@" + tuple.getT2() ;
//    }).subscribe(System.out::println) ;
    System.out.printf("%s - 完成", Thread.currentThread().getName()) ;
    
    System.in.read() ;
  }
  
}

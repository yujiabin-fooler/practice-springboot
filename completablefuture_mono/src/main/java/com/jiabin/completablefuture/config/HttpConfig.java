package com.jiabin.completablefuture.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
public class HttpConfig {

  @Bean
  RestClient restTemplate() {
    return RestClient.create("http://localhost:8080") ;
  }
  
  @Bean
  WebClient webClient() {
    ConnectionProvider connectionProvider = ConnectionProvider.builder("packpool")
        .maxConnections(500) // 最大连接数
        .pendingAcquireMaxCount(-1)
        .build();
    
    HttpClient httpClient = HttpClient.create(connectionProvider)
        .doOnConnected(conn -> conn
            .addHandlerLast(new ReadTimeoutHandler(10))
            .addHandlerLast(new WriteTimeoutHandler(10)));
    
    return WebClient.builder()
        .baseUrl("http://localhost:8080")
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .build() ;
  }
}

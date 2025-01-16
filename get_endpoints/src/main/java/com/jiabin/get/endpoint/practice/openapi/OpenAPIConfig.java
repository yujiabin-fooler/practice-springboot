package com.jiabin.get.endpoint.practice.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

  @Bean
  OpenAPI openAPI() {
    return new OpenAPI()
        .info(
            new Info()
            .title("接口文档")
            .description("所有监控文档")
            .version("v1.0.0")
         );
  }

}

package com.jiabin.http.security.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.ott.JdbcOneTimeTokenService;
import org.springframework.security.authentication.ott.OneTimeTokenAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityAutoConfiguration配置起点，类上@Import SpringBootWebSecurityConfiguration
 * 
 * HttpSecurityConfiguration 类中配置原型Bean HttpSecurity
 * AuthenticationConfiguration 中配置了AuthenticationManager；@EnableGlobalAuthentication中@Import该类
 */
@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain ottFilterChain(HttpSecurity http) throws Throwable {
    http.csrf(csrf -> csrf.disable()) ;
    http
      .authorizeHttpRequests(registry -> {
        registry.requestMatchers("/test/**", "/sent/**", "*.html").permitAll() ;
        registry.anyRequest().authenticated() ;
      })
      // 添加UsernamePasswordAuthenticationFilter过滤器
      .formLogin(Customizer.withDefaults())
      // 用户在系统中必须存在
      // 添加AuthenticationFilter过滤器
      // 该方法会创建OneTimeTokenAuthenticationProvider，也就会添加到自身的认证管理器中
      .oneTimeTokenLogin(configurer -> {
//        configurer.tokenGenerationSuccessHandler((request, response, ott) -> {
//          System.err.printf("username: %s, token: %s, 将于: %s过期%n", 
//              ott.getUsername(), 
//              ott.getTokenValue(), 
//              ott.getExpiresAt()) ;
//        }) ;
      }) ;
    return http.build() ;
  }
  
//  @Bean
  JdbcOneTimeTokenService jdbcOneTimeTokenService(JdbcTemplate jdbcTemplate) {
    JdbcOneTimeTokenService ottService = new JdbcOneTimeTokenService(jdbcTemplate);
    return ottService ;
  }
  
  @Bean
  GlobalAuthenticationConfigurerAdapter customUserConfigurerAdapter() {
    return new GlobalAuthenticationConfigurerAdapter() {

      // 这里配置的用户是不能与OTT共享；OTT是需要UserDetailsService Bean中获取；如果你是基于内存的，那么只会有一个在配置文件中配置的那一个用户
      // 并且这种方式配置的是在父管理器中（AuthenticationManager）
      // OTT认证是通过AuthenticationFilter；OTT认证时自身的认证管理器对象中包含了OneTimeTokenAuthenticationProvider
      // 所以，都走不到父认证器中，直接自己就开始认证了
      @Override
      public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
          .withUser("pack").password("{noop}123123")
          .and()
          .withUser("guest").password("{noop}123123") ;
      }
    };
  }
}
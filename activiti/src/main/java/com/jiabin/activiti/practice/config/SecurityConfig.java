package com.jiabin.activiti.practice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author shiva   2022/3/16 14:04
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 全部跳过认证
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**/**").anonymous()
                .antMatchers(HttpMethod.GET, "/**/**").permitAll();
        //表示所有的访问都必须进行认证处理后才可以正常进行
        http.httpBasic().and().authorizeRequests().anyRequest().fullyAuthenticated();
        //所有的Rest服务一定要设置为无状态，以提升操作性能
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
    }
}

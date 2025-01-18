package com.jiabin.refreshscope.practice.scope;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.web.context.support.StandardServletEnvironment;

public class ContextRefresher {
  
  private Set<String> standardSources = new HashSet<>(
      Arrays.asList(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME,
          StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME,
          StandardServletEnvironment.JNDI_PROPERTY_SOURCE_NAME,
          StandardServletEnvironment.SERVLET_CONFIG_PROPERTY_SOURCE_NAME,
          StandardServletEnvironment.SERVLET_CONTEXT_PROPERTY_SOURCE_NAME, "configurationProperties"));

  private final ConfigurableEnvironment environment ;
  private final RefreshScope refreshScope ;
  
  public ContextRefresher(ConfigurableEnvironment environment, 
      RefreshScope refreshScope) {
    this.environment = environment ;
    this.refreshScope = refreshScope ;
  }
  
  public void refresh() {
    updateEnvironment() ;
    try {
      this.refreshScope.destroy() ;
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  private void updateEnvironment() {
    ConfigurableApplicationContext context = null ;
    try {
      Map<String, Object> map = new HashMap<>();
      map.put("spring.jmx.enabled", false);
      map.put("spring.main.sources", "");
      map.put("spring.main.web-application-type", "NONE");
      StandardEnvironment env = new StandardEnvironment() ;
      env.getPropertySources().addFirst(new MapPropertySource("init", map)) ;
      SpringApplicationBuilder builder = new SpringApplicationBuilder(Empty.class).bannerMode(Banner.Mode.OFF)
          .web(WebApplicationType.NONE).environment(env);
      context = builder.run() ;
      if (env.getPropertySources().contains("init")) {
        env.getPropertySources().remove("init") ;
      }
      MutablePropertySources target = this.environment.getPropertySources();
      String targetName = null;
      for (PropertySource<?> source : env.getPropertySources()) {
        String name = source.getName();
        if (target.contains(name)) {
          targetName = name;
        }
        if (!this.standardSources.contains(name)) {
          if (target.contains(name)) {
            target.replace(name, source);
          } else {
            if (targetName != null) {
              target.addAfter(targetName, source);
              targetName = name;
            } else {
              target.addFirst(source);
              targetName = name;
            }
          }
        }
      }
    } finally {
      if (context != null) {
        context.close() ; 
      }
    }
  }
  
  @Configuration(proxyBeanMethods = false)
  protected static class Empty {
  }
}

package com.jiabin.value.fresh.practice.propertysource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Component;

import com.jiabin.value.fresh.practice.bean.Pair;
import com.jiabin.value.fresh.practice.event.PackValueChangeEvent;

@Component
public class UpdatePropertySource implements EnvironmentAware, ApplicationContextAware {

  public static final String NAME = "packvalue" ;
  
  private ConfigurableEnvironment environment ;
  private ApplicationContext context ; 
  
  public void updateProperty(String key, Object value) {
    MutablePropertySources sources = this.environment.getPropertySources() ;
    PropertySource<?> source = sources.get(NAME) ;
    if (source != null && source instanceof ResourcePropertySource rps) {
      rps.getSource().put(key, value) ;
      this.context.publishEvent(new PackValueChangeEvent(new Pair(key, value))) ;
    }
  }
  
  @Override
  public void setEnvironment(Environment environment) {
    if (environment instanceof ConfigurableEnvironment ce) {
      this.environment = ce ;
    }
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context = applicationContext ; 
  }
}

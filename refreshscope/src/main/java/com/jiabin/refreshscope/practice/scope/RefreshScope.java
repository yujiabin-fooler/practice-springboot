package com.jiabin.refreshscope.practice.scope;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class RefreshScope implements Scope, DisposableBean {

  private final Logger logger = LoggerFactory.getLogger(getClass()) ;
  
  private Map<String, BeanLifecycleWrapper> cache = new ConcurrentHashMap<>() ;
  
  @Override
  public Object get(String name, ObjectFactory<?> objectFactory) {
    BeanLifecycleWrapper wrapper = cache.computeIfAbsent(name, key -> new BeanLifecycleWrapper(name, objectFactory)) ;
    return wrapper.getBean() ;
  }

  @Override
  public Object remove(String name) {
    return cache.remove(name) ;
  }

  @Override
  public void registerDestructionCallback(String name, Runnable callback) {
    // TODO
  }
  
  @Override
  public void destroy() throws Exception {
    cache.forEach((name, wrapper) -> {
      wrapper.destroy() ;
    }) ;
  }

  @Override
  public Object resolveContextualObject(String key) {
    return key ;
  }

  @Override
  public String getConversationId() {
    return null ;
  }
  
  private class BeanLifecycleWrapper {
    private String name ;
    private ObjectFactory<?> objectFactory ;
    private Object bean ;
    public BeanLifecycleWrapper(String name, ObjectFactory<?> objectFactory) {
      this.name = name ;
      this.objectFactory = objectFactory ;
    }
    
    public Object getBean() {
      if (this.bean == null) {
        synchronized (this.name) {
          if (this.bean == null) {
            logger.error("bean: {} 不存在，准备创建", name) ;
            this.bean = this.objectFactory.getObject() ;
          }
        }
      }
      return this.bean ;
    }
    
    public void destroy() {
      this.bean = null ;
    }
    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      BeanLifecycleWrapper other = (BeanLifecycleWrapper) obj;
      if (this.name == null) {
        if (other.name != null) {
          return false;
        }
      }
      else if (!this.name.equals(other.name)) {
        return false;
      }
      return true;
    }
  }

}

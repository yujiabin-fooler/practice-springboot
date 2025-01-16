package com.jiabin.extension.point.practice.autoImportfilter;

import org.springframework.boot.autoconfigure.AutoConfigurationImportFilter;
import org.springframework.boot.autoconfigure.AutoConfigurationMetadata;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import com.jiabin.extension.point.practice.autoconfig.PackAutoConfiguration;

public class PackClassCondition implements AutoConfigurationImportFilter, EnvironmentAware {

  private Environment environment ;
  
  @Override
  public boolean[] match(String[] autoConfigurationClasses, AutoConfigurationMetadata autoConfigurationMetadata) {
    boolean[] matchs = new boolean[autoConfigurationClasses.length] ;
    for (int i = 0, len = matchs.length; i < len; i++) {
      if (autoConfigurationClasses[i] != null && 
          autoConfigurationClasses[i].equals(PackAutoConfiguration.class.getName())) {
        Boolean ret = this.environment.getProperty("pack.app.enabled", Boolean.class, false) ;
       matchs[i] = ret ;
      } else {
        matchs[i] = true ;
      }
    }
    return matchs ;
  }
  @Override
  public void setEnvironment(Environment environment) {
    this.environment = environment ;
  }
}

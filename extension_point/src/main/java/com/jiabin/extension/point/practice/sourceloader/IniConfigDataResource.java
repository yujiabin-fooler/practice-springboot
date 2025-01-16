package com.jiabin.extension.point.practice.sourceloader;

import org.springframework.boot.context.config.ConfigDataLocation;
import org.springframework.boot.context.config.ConfigDataResource;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class IniConfigDataResource extends ConfigDataResource {

  private final ConfigDataLocation location ;
  private final PropertySourceLoader loader ;
  private final Resource resource ;
  
  public IniConfigDataResource(ConfigDataLocation location, PropertySourceLoader loader, Resource resource) {
    this.location = location ;
    this.loader = loader ;
    this.resource = resource ;
  }
  
  public IniConfigDataResource(ConfigDataLocation location) {
    this.location = location ;
    this.loader = new PropertiesPropertySourceLoader() ;
    this.resource = new ClassPathResource(location.getValue()) ;
  }
  
  public PropertySourceLoader getLoader() {
    return loader ;
  }
  public ConfigDataLocation getLocation() {
    return location ;
  }
  public Resource getResource() {
    return resource ;
  }

  @Override
  public String toString() {
    return "IniConfigDataResource [resource=" + resource + "]";
  }
}

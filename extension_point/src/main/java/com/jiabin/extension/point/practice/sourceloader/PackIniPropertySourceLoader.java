package com.jiabin.extension.point.practice.sourceloader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

public class PackIniPropertySourceLoader implements PropertySourceLoader {

  @Override
  public String[] getFileExtensions() {
    return new String[] { "ini" };
  }

  @Override
  public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
    Properties props = new Properties() ;
    props.load(resource.getInputStream()) ;
    List<PropertySource<?>> result = new ArrayList<>() ;
    result.add(new PropertiesPropertySource(name, props)) ;
    return result ;
  }
}

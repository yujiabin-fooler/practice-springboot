package com.jiabin.extension.point.practice.sourceloader;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.context.config.ConfigData;
import org.springframework.boot.context.config.ConfigDataLoader;
import org.springframework.boot.context.config.ConfigDataLoaderContext;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.core.env.PropertySource;

public class PackIniConfigDataLoader implements ConfigDataLoader<IniConfigDataResource> {

  private static final String SUFFIX = "ini" ;
  
  // 上一步通过ConfigDataLocationResolver确定了要加载的文件
  // 这里就判断当前的加载器是否可以加载
  @Override
  public boolean isLoadable(ConfigDataLoaderContext context, IniConfigDataResource resource) {
    return resource.getResource().getFilename().endsWith(SUFFIX) ;
  }
  
  @Override
  public ConfigData load(ConfigDataLoaderContext context, IniConfigDataResource resource)
      throws IOException, ConfigDataResourceNotFoundException {
    List<PropertySource<?>> propertySources = resource.getLoader().load(resource.getResource().toString(), resource.getResource()) ;
    return new ConfigData(propertySources) ;
  }
}

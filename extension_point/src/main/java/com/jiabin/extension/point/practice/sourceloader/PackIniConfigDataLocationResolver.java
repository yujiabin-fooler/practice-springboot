package com.jiabin.extension.point.practice.sourceloader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.context.config.ConfigDataLocation;
import org.springframework.boot.context.config.ConfigDataLocationNotFoundException;
import org.springframework.boot.context.config.ConfigDataLocationResolver;
import org.springframework.boot.context.config.ConfigDataLocationResolverContext;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.SpringFactoriesLoader;

/**
 * 如果自定义了ConfigDataLocationResolver，如果isResolvable方法返回true，那么将会覆盖系统默认的StandardConfigDataLocationResolver
 * 这样就不会处理默认的application.yml[properties]。
 * 
 * 在ConfigDataLocationResolvers构造函数中你就知道你的PackIniConfigDataLocationResolver构造函数可以有哪些参数了
 */
public class PackIniConfigDataLocationResolver implements ConfigDataLocationResolver<IniConfigDataResource> {

	private final List<PropertySourceLoader> propertySourceLoaders;
	private final ResourceLoader resourceLoader;
	private final Binder binder ;
	public PackIniConfigDataLocationResolver(ResourceLoader resourceLoader, Binder binder) {
	  this.propertySourceLoaders = SpringFactoriesLoader.loadFactories(PropertySourceLoader.class,
        getClass().getClassLoader()) ;
    this.resourceLoader = resourceLoader ;
    this.binder = binder ;
  }
	
  @Override
  public boolean isResolvable(ConfigDataLocationResolverContext context, ConfigDataLocation location) {
    return true ;
  }

  // location : optional:classpath:/;optional:classpath:/config/
  @Override
  public List<IniConfigDataResource> resolve(ConfigDataLocationResolverContext context, ConfigDataLocation location)
      throws ConfigDataLocationNotFoundException, ConfigDataResourceNotFoundException {
    ConfigDataLocation[] locations = location.split() ;
    List<IniConfigDataResource> result = new ArrayList<>() ;
    BindResult<String> bind = this.binder.bind("app.config.name", String.class) ;
    String name = bind.orElse("info") ;
    String sysName = this.binder.bind("spring.config.name", String.class).orElse("application") ;
    PropertySourceLoader loader = getLoader() ;
    for (ConfigDataLocation cdl : locations) {
      String value = cdl.getValue() + name + ".ini" ;
      Resource resource = this.resourceLoader.getResource(value) ;
      if (resource.exists()) {
        result.add(new IniConfigDataResource(ConfigDataLocation.of(value), loader, resource)) ;
      }
    }
    // 只要定义好要加载的文件即可
    result.add(new IniConfigDataResource(
        ConfigDataLocation.of("d:\\data\\my.ini"), 
        loader, new FileSystemResource(new File("d:\\data\\my.ini")))) ;
    return result ;
  }
  
  private PropertySourceLoader getLoader() {
    for (PropertySourceLoader loader : this.propertySourceLoaders) {
      if (Arrays.asList(loader.getFileExtensions()).contains("ini")) {
        return loader ;
      }
    }
    return new PropertiesPropertySourceLoader() ;
  }
  
  public List<PropertySourceLoader> getPropertySourceLoaders() {
    return propertySourceLoaders;
  }
  public ResourceLoader getResourceLoader() {
    return resourceLoader;
  }
}

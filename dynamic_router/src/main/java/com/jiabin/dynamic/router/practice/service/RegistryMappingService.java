package com.jiabin.dynamic.router.practice.service;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo.BuilderConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPatternParser;

import com.jiabin.dynamic.router.practice.domain.Mapping;
import com.jiabin.dynamic.router.practice.loader.PackHandlerClassLoader;

@Service
public class RegistryMappingService {
  
  public static final Map<Object, Method[]> METHOD_CACHE = new ConcurrentHashMap<>() ;

  private final AnnotationBeanNameGenerator beanNameGenerator ;
  private final CachingMetadataReaderFactory metadataReaderFactory ;
  
  private final RequestMappingHandlerMapping requestMappingHandlerMapping ;
  private final ApplicationContext context ;
  public RegistryMappingService(RequestMappingHandlerMapping requestMappingHandlerMapping, 
      ApplicationContext context) {
    this.requestMappingHandlerMapping = requestMappingHandlerMapping ;
    this.context = context ;
    this.beanNameGenerator = new AnnotationBeanNameGenerator() ;
    this.metadataReaderFactory = new CachingMetadataReaderFactory() ;
  }
  
  public boolean register(Mapping mapping) {
    BuilderConfiguration options = new BuilderConfiguration() ;
    options.setPatternParser(new PathPatternParser()) ;
    RequestMappingInfo info = RequestMappingInfo.paths(mapping.getPath())
        .methods(RequestMethod.resolve(mapping.getRequestMethod()))
        .options(options)
        .build() ;
    
    Object handler = this.context.getBean(mapping.getBeanName()) ;
    // 我们先排除重载的方法，直接通过方法名查找对应的method
    Method[] methods = METHOD_CACHE.computeIfAbsent(handler, key -> ReflectionUtils.getAllDeclaredMethods(handler.getClass())) ;
    Method method = Arrays.stream(methods)
        .filter(m -> m.getName().equals(mapping.getMethodName()))
        .findFirst()
        .orElse(null) ;
    if (method == null) {
      return false ;
    }
    this.requestMappingHandlerMapping.registerMapping(info, handler, method) ;
    return true ;
  }
  
  /**
   * 我们只需要填写对应的path和requestMethod即可
   * @param mapping
   * @return
   */
  public boolean unregister(Mapping mapping) {
    BuilderConfiguration options = new BuilderConfiguration() ;
    options.setPatternParser(new PathPatternParser()) ;
    RequestMappingInfo info = RequestMappingInfo.paths(mapping.getPath())
        .methods(RequestMethod.resolve(mapping.getRequestMethod()))
        .options(options)
        .build() ;
    this.requestMappingHandlerMapping.unregisterMapping(info) ;
    return true ;
  }
  
  public void registerBean(File file) {
    try {
      FileSystemResource resource = new FileSystemResource(file);
      // 获取元数据信息
      MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource) ;
      String className = metadataReader.getClassMetadata().getClassName();
      PackHandlerClassLoader classLoader = new PackHandlerClassLoader(file) ;
      Class<?> clazz = classLoader.loadClass(className) ;
      AnnotatedBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(clazz) ;
      AutowireCapableBeanFactory beanFactory = this.context.getAutowireCapableBeanFactory() ;
      Object instance = clazz.getDeclaredConstructor().newInstance() ;
      beanFactory.autowireBean(instance) ;
      beanFactory.initializeBean(instance, instance.toString()) ;
      if (beanFactory instanceof DefaultListableBeanFactory bf) {
        String beanName = this.beanNameGenerator.generateBeanName(beanDefinition, bf) ;
        bf.registerSingleton(beanName, instance) ;
      }
    } catch (Exception e) {
      e.printStackTrace() ;
    }
  }
  
  public static void main(String[] args) throws Exception {
    PackHandlerClassLoader classLoader = new PackHandlerClassLoader(new File("e:\\router\\ProductHandler.class")) ;
    Class<?> clazz = classLoader.loadClass("com.pack.handler.ProductHandler") ;
    Method method = clazz.getMethod("save") ;
    System.out.println(method.isAnnotationPresent(ResponseBody.class)) ;
  } 
}

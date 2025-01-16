package com.jiabin.mvc.practice.mapping;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import com.jiabin.mvc.practice.annotation.PackController;
import com.jiabin.mvc.practice.annotation.PackRequest;

import jakarta.servlet.http.HttpServletRequest;

@Component
@Order(-1)
public class PackHandlerMapping implements HandlerMapping, InitializingBean, ApplicationContextAware {

  private static final Logger logger = LoggerFactory.getLogger(PackHandlerMapping.class) ;
  
  private static final String SCOPED_TARGET_NAME_PREFIX = "scopedTarget.";
  
  private ApplicationContext context ;
  
  private Map<String, PackRequestInfo> requests = new ConcurrentHashMap<>() ;
  
  @Override
  public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
    String path = getRequestPath(request) ;
    PackRequestInfo packRequestInfo = requests.get(path) ;
    if (packRequestInfo == null) {
      return null ;
    }
    HandlerExecutionChain chain = new HandlerExecutionChain(packRequestInfo) ;
    return chain ;
  }
  
  private String getRequestPath(HttpServletRequest request) {
    String requestUri = request.getRequestURI() ;
    return requestUri ;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    for (String beanName : getCandidateBeanNames()) {
      if (!beanName.startsWith(SCOPED_TARGET_NAME_PREFIX)) {
        processCandidateBean(beanName);
      }
      this.context.getType(beanName) ;
    }
  }
  
  protected void processCandidateBean(String beanName) {
    Class<?> beanType = null;
    try {
      beanType = this.context.getType(beanName);
    } catch (Throwable ex) {
      logger.error("{}", ex) ;
    }
    if (beanType != null && isHandler(beanType)) {
      detectHandlerMethods(beanName, beanType);
    }
  }
  
  /**
   * 解析获取符合条件的类，并从该类中获取有@PackRequest注解的方法
   * @param handler
   * @param handlerType
   */
  private void detectHandlerMethods(Object handler, Class<?> handlerType) {
    if (handlerType != null) {
      Class<?> userType = ClassUtils.getUserClass(handlerType);
      Map<Method, PackRequestInfo> methods = MethodIntrospector.selectMethods(userType, (MethodIntrospector.MetadataLookup<PackRequestInfo>) method -> {
        PackRequest request = method.getAnnotation(PackRequest.class) ;
        if (request != null) {
          // 注意：这里的handler目前还是字符串，值为beanName;我们
          return new PackRequestInfo(request.value(), method, handler) ;
        }
        return null ;
      }) ;
      // 这里简单起见，我们不考虑请求uri相同，method不同的情况; 完全通过uri进行匹配。
      methods.forEach((method, info) -> {
        requests.putIfAbsent(info.getPath(), info) ;
      }) ;
    }
  }
  
  /**
   * 只有类上有@PackController注解的才进行处理
   * @param beanType
   * @return
   */
  private boolean isHandler(Class<?> beanType) {
    return AnnotatedElementUtils.hasAnnotation(beanType, PackController.class);
  }
  
  /**
   * 从当前容器中获取所有的bean，包括从父容器中获取
   * @return
   */
  protected String[] getCandidateBeanNames() {
    return BeanFactoryUtils.beanNamesForTypeIncludingAncestors(this.context, Object.class) ;
  }

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    this.context = context ;
  }
  
}

package com.jiabin.aop.authority.practice.interceptor;

import com.jiabin.aop.authority.practice.controller.ApiController;
import com.jiabin.aop.authority.practice.domain.Permission;
import com.jiabin.aop.authority.practice.domain.User;
import com.jiabin.aop.authority.practice.service.PermissionService;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthInterceptor implements HandlerInterceptor {

  SpelExpressionParser parser = new SpelExpressionParser() ;
  DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer() ;
  
  private final PermissionService permissionService ;
  public AuthInterceptor(PermissionService permissionService) {
    this.permissionService = permissionService ;
  }
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (handler instanceof HandlerMethod hm) {
      HandlerMethod handlerMethod = (HandlerMethod) handler;
      PreAuthorize preAuthorize = handlerMethod.getMethodAnnotation(PreAuthorize.class);
      if (preAuthorize != null) {
        User user = SecurityContext.get() ;
        if (user == null) {
          response.getWriter().write("Goto login");
          return false ;
        }
        String expressionString = preAuthorize.value();
        MethodBasedEvaluationContext context = createContext(hm) ;
        Object value = parser.parseExpression(expressionString).getValue(context) ;
        if (value instanceof String) {
          List<String> allowedPermissions = Arrays.asList(value.toString()) ;
          List<Permission> permissions = this.permissionService.findPermissions(user.getId()) ;
          if (!hasAllowedPermission(allowedPermissions, permissions)) {
            response.getWriter().write("Access denied");
            return false ;
          }
        } else if (value instanceof Boolean ret) {
          if (!ret) {
            response.getWriter().write("Access denied");
            return ret ;
          }
          return ret ;
        }
      }
    }
    return true ;
  }
  
  private MethodBasedEvaluationContext createContext(HandlerMethod hm) {
    Method method = hm.getMethod() ;
    User rootObject = SecurityContext.get() ;
    System.out.println(rootObject.getUsername()) ;
    MethodBasedEvaluationContext context = new MethodBasedEvaluationContext(rootObject, method, new Object[] {}, parameterNameDiscoverer) ;
    return context ;
  }
  
  private boolean hasAllowedPermission(List<String> allowedPermissions, List<Permission> permissions) {
    List<String> permissionNames = permissions.stream().map(Permission::getName).collect(Collectors.toList());
    return allowedPermissions.stream().anyMatch(permissionNames::contains);
  }
  
  public static void main(String[] args) throws Exception {
    SpelExpressionParser parser = new SpelExpressionParser() ;
    DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer() ;
    Method method = ApiController.class.getDeclaredMethod("save") ;
    User rootObject = new User(666L) ;
    rootObject.setUsername("admin") ;
    MethodBasedEvaluationContext context = new MethodBasedEvaluationContext(rootObject, method, new Object[] {1L}, parameterNameDiscoverer) ;
    String expressionString = "'api.save'";
    Object value = parser.parseExpression(expressionString).getValue(context) ;
    if (value instanceof String) {
      System.err.println("String " + value) ;
    }
    expressionString = "id == 666" ;
    System.out.println(parser.parseExpression(expressionString).getValue(context)) ;
    expressionString = "username eq 'admin'" ;
    System.out.println(parser.parseExpression(expressionString).getValue(context)) ;
    System.out.println(parser.parseExpression(expressionString).getValue(context) instanceof Boolean) ;
  }
  
}

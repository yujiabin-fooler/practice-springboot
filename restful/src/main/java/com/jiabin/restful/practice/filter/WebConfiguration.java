package com.jiabin.restful.practice.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiabin.restful.practice.config.IpConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
* Title: WebConfiguration
* Description: 
* 自定义过滤器 
* Version:1.0.0
 */
@Configuration
public class WebConfiguration {
    
	@Autowired  
    private IpConfig ipconfig;
	
    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        //过滤掉 /getUser 和/hello 的请求
        registration.addUrlPatterns("/getUser","/hello");
        //过滤掉所有请求
//      registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }
    
    
     class MyFilter implements Filter {
		@Override
		public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
				throws IOException, ServletException {
			HttpServletRequest request = (HttpServletRequest) srequest;
			HttpServletResponse response = (HttpServletResponse) sresponse;
			//执行过滤操作...
			System.out.println("请求的url :"+request.getRequestURI());
			//检查是否是白名单的IP
			if(!checkIP(request,response)){
				return;
			}
			filterChain.doFilter(srequest, sresponse);
		}

		@Override
		public void init(FilterConfig filterConfig) throws ServletException {
			System.out.println("参数初始化:"+filterConfig);
		}
		
		@Override
		public void destroy() {
			System.out.println("开始销毁...");
		}
    }
    
     private boolean checkIP(HttpServletRequest request,HttpServletResponse response){
    	  String ip = getIpAddr(request);
          // 获取可以访问系统的白名单
          String ipStr = ipconfig.getIpWhiteList();
          String[] ipArr = ipStr.split("\\|");
          List<String> ipList = Arrays.asList(ipArr);
          if (ipList.contains(ip)) {
          	 System.out.println("该IP: " + ip+"通过!");
              return true;
          } else {
              System.out.println("该IP: " + ip+"不通过!");
          	try {
              response.setCharacterEncoding("UTF-8");
              response.setContentType("application/json; charset=utf-8");
              // 消息
              Map<String, Object> messageMap = new HashMap<>();
              messageMap.put("status", "1");
              messageMap.put("message", "您好，ip为" + ip + ",暂时没有访问权限，请联系管理员开通访问权限。");
              ObjectMapper objectMapper=new ObjectMapper();
              String writeValueAsString;
			  writeValueAsString = objectMapper.writeValueAsString(messageMap);
              response.getWriter().write(writeValueAsString);
        	 } catch (Exception e) {
				e.printStackTrace();
			 }
              return false;
          }
     }
     

     /**
      * 获取访问的ip地址
      * 
      * @param request
      * @return
      */
     private  String getIpAddr(HttpServletRequest request) {
         String ip = request.getHeader("X-Forwarded-For");
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
             ip = request.getHeader("Proxy-Client-IP");
         }
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
             ip = request.getHeader("WL-Proxy-Client-IP");
         }
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
             ip = request.getHeader("HTTP_CLIENT_IP");
         }
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
             ip = request.getHeader("HTTP_X_FORWARDED_FOR");
         }
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
             ip = request.getRemoteAddr();
         }
         return ip;
     }
}
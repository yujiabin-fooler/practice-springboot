package com.jiabin.http.security.practice.config;

import java.io.IOException;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class PackOneTimeTokenGenerationSuccessHandler implements OneTimeTokenGenerationSuccessHandler {

  private final OneTimeTokenGenerationSuccessHandler redirectHandler = new RedirectOneTimeTokenGenerationSuccessHandler("/sent/ott");
  
  private final JavaMailSender mailSender ;
  public PackOneTimeTokenGenerationSuccessHandler(JavaMailSender mailSender) {
    this.mailSender = mailSender ;
  }
  
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, OneTimeToken ott)
      throws IOException, ServletException {
//    ZonedDateTime zdt = ott.getExpiresAt().atZone(ZoneId.systemDefault());
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") ;
//    System.err.printf("username: %s, token: %s, 将于: %s过期%n", 
//        ott.getUsername(), 
//        ott.getTokenValue(),
//        formatter.format(zdt)) ;
    System.err.printf("给用户: %s, 生成一次性Token%n", ott.getUsername()) ;
    
    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(UrlUtils.buildFullRequestUrl(request))
        .replacePath(request.getContextPath())
        .replaceQuery(null)
        .fragment(null)
        .path("/login/ott")
        .queryParam("token", ott.getTokenValue()); 
    String magicLink = builder.toUriString() ;
    System.err.printf("生成的链接: %s%n", magicLink) ;
    // String email = getUserEmail(oneTimeToken.getUsername()); 
    // this.mailSender.send(email, "Your Spring Security One Time Token", "Use the following link to sign in into the application: " + magicLink);
    SimpleMailMessage mailMessage = new SimpleMailMessage() ;
    mailMessage.setSubject("Spring全家桶实战案例源码 - OTT登录认证") ;
    mailMessage.setFrom("348792955@qq.com") ;
    SimpleMailMessage msg = new SimpleMailMessage(mailMessage);
    msg.setTo("lovemarry@vip.qq.com") ;
    msg.setText("OTT认证, <a href=\"" + magicLink + "\">点击登录</a>") ;
    try {
      this.mailSender.send(msg) ;
    } catch (MailException ex) {
      System.err.printf("邮件发送错误：%s%n", ex.getMessage()) ; 
    }
    this.redirectHandler.handle(request, response, ott) ; 
  }
}

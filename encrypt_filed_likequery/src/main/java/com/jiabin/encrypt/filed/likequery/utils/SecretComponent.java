package com.jiabin.encrypt.filed.likequery.utils;

import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class SecretComponent {

  static {
    Security.addProvider(new BouncyCastleProvider()) ;
  }
  
  @Value("${pack.app.secretKey}")
  private String secretKey ;
  private SecretKey key ;
  
  @PostConstruct
  public void initKey() {
    try {
      this.key = new SecretKeySpec(this.secretKey.getBytes(), "SM4") ;
    } catch (Exception e) {
      throw new RuntimeException("不支持的算法错误", e) ;
    }
  }
  
  public String encrypt(String plainText) {
    try {
      Cipher cipher = Cipher.getInstance("SM4") ;
      cipher.init(Cipher.ENCRYPT_MODE, key) ;
      byte[] result = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8)) ;
      return Base64.getEncoder().encodeToString(result) ;
    } catch (Exception e) {
      throw new RuntimeException("加密算法执行错误：" + e.getMessage()) ;
    }
  }
  
  public String decrypt(String cipherText) {
    try {
      Cipher cipher = Cipher.getInstance("SM4") ;
      cipher.init(Cipher.DECRYPT_MODE, key) ;
      byte[] result = cipher.doFinal(Base64.getDecoder().decode(cipherText)) ;
      return new String(result, StandardCharsets.UTF_8) ;
    } catch (Exception e) {
      throw new RuntimeException("加密算法执行错误：" + e.getMessage()) ;
    }
  }
  
}

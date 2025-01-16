package com.jiabin.api.secret.signature.practice.crypto;

import com.jiabin.api.secret.signature.practice.domain.User;

public class MD5SignatureResolver extends AbstractSignatureResolver {

  @Override
  protected String getAlgorithm() {
    return "MD5" ;
  }
  
  public static void main(String[] args) {
    User user = new User(1L, "涨知识", 22) ;
    SignatureResolver resolver = new MD5SignatureResolver() ;
    String ret = resolver.signature(user) ;
    System.err.println(ret) ;
  }
}

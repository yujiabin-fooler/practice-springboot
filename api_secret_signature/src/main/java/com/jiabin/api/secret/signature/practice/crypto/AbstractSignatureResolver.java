package com.jiabin.api.secret.signature.practice.crypto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import com.jiabin.api.secret.signature.practice.utils.HashUtils;
import com.jiabin.api.secret.signature.practice.utils.Hex;

public abstract class AbstractSignatureResolver implements SignatureResolver {

  @Override
  public String signature(Object source) {
    try {
      MessageDigest md = MessageDigest.getInstance(getAlgorithm()) ;
      byte[] ret = md.digest(HashUtils.genHashContent(source).getBytes(StandardCharsets.UTF_8)) ;
      return Hex.encode(ret) ;
    } catch (Exception e) {
      throw new RuntimeException(e) ;
    }
  }
  protected abstract String getAlgorithm() ;
}

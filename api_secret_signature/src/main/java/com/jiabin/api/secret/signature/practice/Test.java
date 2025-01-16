package com.jiabin.api.secret.signature.practice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Test {

  public static void main(String[] args) throws Exception {
    ObjectMapper mapper = new ObjectMapper() ;
    ObjectWriter writer = mapper.writer() ;
    String ret = writer.writeValueAsString("data") ;
    System.out.println(ret) ;
  }
  
}

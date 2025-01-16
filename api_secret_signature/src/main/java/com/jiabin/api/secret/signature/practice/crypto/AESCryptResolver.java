package com.jiabin.api.secret.signature.practice.crypto;

public class AESCryptResolver extends AbstractCryptResolver {
	
  @Override
  protected String getAlgorithm() {
    return "AES" ;
  }
  
  public static void main(String[] args) {
    AESCryptResolver aes = new AESCryptResolver();
    String data = "" ;
    data = "{\"id\": 666, \"name\": \"张三\", \"age\": 23}";
    // data = "大家快乐飞8234u9234%……&*";
    String secret = "aaaabbbbccccddddd";
    String result =  aes.encrypt(secret, data) ;
    System.out.println("加密结果：" + result);
    System.out.println("解密结果：" + aes.decrypt(secret, result));
    System.err.println("---------------------------------") ;
    data = "d7d57fb4d3812ced8acd94cb0e4743b819d441d9fbe4119d82bf4e4f77654fe2c461e6ebfe8d67e2e756c2af23b7574ea6159adc593bcebf5553c1d1a834714fe20c02fef8a1520f1ade71051f9bd7d2";
    System.out.println("解密数据:" + aes.decrypt(secret, data)) ;
  }
}

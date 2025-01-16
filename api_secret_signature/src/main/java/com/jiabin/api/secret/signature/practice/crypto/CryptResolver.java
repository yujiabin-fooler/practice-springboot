package com.jiabin.api.secret.signature.practice.crypto;

public interface CryptResolver {
	
	String encrypt(String secret, String source) ;
	
	String decrypt(String secret, String source) ;
}

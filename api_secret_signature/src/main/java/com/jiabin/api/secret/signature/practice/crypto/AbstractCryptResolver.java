package com.jiabin.api.secret.signature.practice.crypto;

import java.security.Key;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.jiabin.api.secret.signature.practice.utils.Hex;

public abstract class AbstractCryptResolver implements CryptResolver {

	@Override
	public final String encrypt(String secret, String source) {
		try {
			Cipher cipher = Cipher.getInstance(getAlgorithm()) ;
			cipher.init(Cipher.ENCRYPT_MODE, secretKey(secret)) ;
			return Hex.encode(cipher.doFinal(source.getBytes())) ;
		} catch (Exception e) {
			throw new RuntimeException(e) ;
		}
	}
	
	@Override
	public final String decrypt(String secret, String encrtptText) {
		try {
			Cipher cipher = Cipher.getInstance(getAlgorithm()) ;
			cipher.init(Cipher.DECRYPT_MODE, secretKey(secret)) ;
			return new String(cipher.doFinal(Hex.decode(encrtptText))) ;
		} catch (Exception e) {
      throw new RuntimeException(e) ;
    }
	}
	
	protected abstract String getAlgorithm() ;

	private Key secretKey(String secret) {
	  byte[] keys = Arrays.copyOf(secret.getBytes(), 16) ;
		return new SecretKeySpec(keys, getAlgorithm()) ;
	}
}

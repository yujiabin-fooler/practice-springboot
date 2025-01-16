package com.jiabin.encrypt.filed.likequery.utils;

import java.util.Properties;

public class LikeQueryUtils {

	private static final CharDigestLikeEncryptAlgorithm alg ;
	
	static {
		alg = new CharDigestLikeEncryptAlgorithm() ;
		alg.init(new Properties()) ;
	}
	
	public static String encrypt(String plainText) {
		return alg.encrypt(plainText) ;
	}
}

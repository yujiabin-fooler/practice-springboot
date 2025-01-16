package com.jiabin.api.secret.signature.practice.utils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.TreeMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HashUtils {
	
	private static final String SPLIT_CHAR = "&" ;
	private static final String CONCAT_CHAR = "=" ;

	public static String genHashContent(Object source) throws Exception {
		if (Objects.isNull(source)) {
			return null ;
		}
		if (source instanceof String) {
			return (String) source ;
		}
		if (source instanceof Double) {
			return String.valueOf(source) ;
		}
		if (source instanceof Boolean) {
			return ((Boolean)source).toString() ;
		}
		if (source instanceof Integer) {
			return source.toString() ;
		}
		StringJoiner joiner = new StringJoiner(SPLIT_CHAR) ;
		if (source instanceof List lists) {
			for(Object item : lists) {
				joiner.add(genHashContent(item)) ;
			}
			return joiner.toString() ;
		}
		ObjectMapper mapper = new ObjectMapper() ;
		String result = mapper.writeValueAsString(source) ;
		TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String,Object>>() {} ;
		Map<String, Object> map = mapper.readValue(result, typeReference) ;
		TreeMap<String, Object> res = new TreeMap<>(map) ;
		for (Map.Entry<String, Object> entry : res.entrySet()) {
			String key = entry.getKey() ;
			Object val = entry.getValue() ;
			if (Objects.nonNull(val) && val != "") {
				if (val instanceof Map) {
					joiner.add(genHashContent(val)) ;
				} else if (val instanceof List lists) {
					for(Object item : lists) {
						joiner.add(genHashContent(item)) ;
					}
				} else {
					joiner.add(key + CONCAT_CHAR + val) ;
				}
			}
		}
		return joiner.toString() ;
	}
}

package com.jiabin.sensitive.word.check.practice.config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import com.github.houbb.sensitive.word.api.IWordAllow;

public class CustomWordAllow implements IWordAllow {
	private static final Logger logger = LoggerFactory.getLogger(CustomWordAllow.class) ;
	
	private final Resource resource ;
	public CustomWordAllow(Resource resource) {
		this.resource = resource ;
	}
	
	@Override
	public List<String> allow() {
	  if (!this.resource.exists()) {
	    return List.of() ;
	  }
		List<String> list = new ArrayList<String>();
		try (InputStream is = this.resource.getInputStream() ;
	      BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
			String line = null ;
			while ((line = reader.readLine()) != null) {
				list.add(line) ;
			}
			reader.close(); 
			is.close() ; 
		} catch (Exception e) {
			logger.error("读取非敏感词文件错误, {}",e ) ;
		}
		return list;
	}
}
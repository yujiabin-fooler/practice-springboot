package com.jiabin.sensitive.word.check.practice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.github.houbb.sensitive.word.api.IWordAllow;
import com.github.houbb.sensitive.word.api.IWordDeny;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.deny.WordDenys;

@Configuration
public class SensitiveConfig {
	
	private static final String SENSITIVE_WORD_FILE = "classpath:custom-sensitive-words.txt" ;
	private static final String WORD_ALLOW_FILE = "classpath:custom-allow-words.txt" ;
	
	@Bean
	IWordDeny customWordDeny(@Value(SENSITIVE_WORD_FILE) Resource resource) {
		return WordDenys.chains(WordDenys.defaults(), new CustomWordDeny(resource)) ;
	}
	@Bean
	IWordAllow customWordAllow(@Value(WORD_ALLOW_FILE) Resource resource) {
		return WordAllows.chains(WordAllows.defaults(), new CustomWordAllow(resource))  ;
	}
	
	@Bean
	SensitiveWordBs sensitiveWordBs(IWordDeny customWordDeny, IWordAllow customWordAllow) {
		return SensitiveWordBs.newInstance()
				// 忽略大小写
				.ignoreCase(true)
				// 忽略半角圆角
				.ignoreWidth(true)
				// 忽略数字的写法
				.ignoreNumStyle(true)
				// 忽略中文的书写格式：简繁体
				.ignoreChineseStyle(true)
				// 忽略英文的书写格式
				.ignoreEnglishStyle(true)
				// 忽略重复词
				.ignoreRepeat(false)
				// 是否启用数字检测
				.enableNumCheck(true)
				// 是否启用邮箱检测
				.enableEmailCheck(true)
				// 是否启用链接检测
				.enableUrlCheck(true)
				// 数字检测，自定义指定长度
				.numCheckLen(8)
				// 配置自定义敏感词
				.wordDeny(customWordDeny)
				// 配置非自定义敏感词
				.wordAllow(customWordAllow)
				.init() ;
	}

}

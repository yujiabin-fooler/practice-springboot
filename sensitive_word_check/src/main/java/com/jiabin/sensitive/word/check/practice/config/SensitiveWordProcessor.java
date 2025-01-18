package com.jiabin.sensitive.word.check.practice.config;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;

@Component
public class SensitiveWordProcessor {

	private final SensitiveWordBs sensitiveWordBs ;
	public SensitiveWordProcessor(SensitiveWordBs sensitiveWordBs) {
		this.sensitiveWordBs = sensitiveWordBs ;
	}

	/** 刷新敏感词库与非敏感词库缓存 */
	public void refresh() {
		sensitiveWordBs.init() ;
	}
	
	public void add(Collection<String> words) {
		sensitiveWordBs.addWord(words);
	}

	/** 判断是否含有敏感词 */
	public boolean contains(String text) {
		return sensitiveWordBs.contains(text);
	}

	/** 使用默认替换符 * 进行替换敏感词 */
	public String replace(String text) {
		return sensitiveWordBs.replace(text);
	}

	/** 返回所有敏感词 */
	public List<String> findAll(String text) {
		return sensitiveWordBs.findAll(text);
	}
}
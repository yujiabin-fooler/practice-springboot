package com.jiabin.encrypt.filed.likequery.handler;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jiabin.encrypt.filed.likequery.utils.SecretComponent;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Component
@Converter
public class EntityAttributeConverter implements AttributeConverter<String, String> {

	private final SecretComponent sc;
	public EntityAttributeConverter(SecretComponent sc) {
	  this.sc = sc ;
  }
	
	@Override
	public String convertToDatabaseColumn(String attribute) {
		if (StringUtils.hasLength(attribute)) {
			return this.sc.encrypt(attribute) ;
		}
		return attribute ;
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		if (StringUtils.hasLength(dbData)) {
			return this.sc.decrypt(dbData) ;
		}
		return dbData ;
	}
}

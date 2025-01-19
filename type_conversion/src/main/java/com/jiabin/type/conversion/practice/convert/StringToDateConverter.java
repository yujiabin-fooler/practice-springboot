package com.jiabin.type.conversion.practice.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDateConverter implements Converter<String, Date> {

  @Override
  public Date convert(String source) {
    try {
      return  new SimpleDateFormat("yyyy-MM-dd").parse(source) ;
    } catch (ParseException e) {
      e.printStackTrace() ; 
      return null ;
    }
  }
}

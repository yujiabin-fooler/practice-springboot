package com.jiabin.value.fresh.practice.bean;

import java.lang.reflect.Field;

import org.springframework.beans.TypeConverter;

public class PackValueMetadata {

  private Field field ;
  private Object bean ;
  private TypeConverter converter ;
  public PackValueMetadata() {
  }
  public PackValueMetadata(Field field, Object bean, TypeConverter converter) {
    this.field = field;
    this.bean = bean;
    this.converter = converter ;
  }
  
  public void invokeValue(Object value) {
    field.setAccessible(true) ;
    try {
      // 类型转换
      value = this.converter.convertIfNecessary(value, this.field.getType()) ;
      field.set(this.bean, value) ;
    } catch (Exception e) {
      e.printStackTrace() ;
    }
  }
}

package com.jiabin.sensitive.word.check.practice.validator;

import org.springframework.stereotype.Component;

import com.jiabin.sensitive.word.check.practice.annotation.Sensitive;
import com.jiabin.sensitive.word.check.practice.config.SensitiveWordProcessor;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class SensitiveValidator implements ConstraintValidator<Sensitive, CharSequence> {
  
  private final SensitiveWordProcessor processor ;
  public SensitiveValidator(SensitiveWordProcessor processor) {
    this.processor = processor ;
  }
  @Override
  public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
    if (value == null) {
      return true ;
    }
    return !this.processor.contains(value.toString()) ;
  }
  @Override
  public void initialize(Sensitive sensitive) {
  }
}
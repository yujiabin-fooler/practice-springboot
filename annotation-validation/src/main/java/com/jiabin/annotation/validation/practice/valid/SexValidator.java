package com.jiabin.annotation.validation.practice.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * SexValidator
 * </p>
 *
 * @author jiabin.yu
 * @since 2024/6/12
 */
public class SexValidator implements ConstraintValidator<Sex, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Set<String> sexSet = new HashSet<String>();
        sexSet.add("男");
        sexSet.add("女");
        return sexSet.contains(value);
    }
}

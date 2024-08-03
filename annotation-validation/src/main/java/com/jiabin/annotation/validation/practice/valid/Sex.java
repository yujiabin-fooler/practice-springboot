package com.jiabin.annotation.validation.practice.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>
 * Sex
 * </p>
 *
 * @author jiabin.yu
 * @since 2024/6/12
 */
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = SexValidator.class)
@Documented
public @interface Sex {

    String message() default "性别值不在可选范围内";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

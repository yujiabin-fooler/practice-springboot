package com.jiabin.data.desensitization.practice.sensitive;


import com.jiabin.data.desensitization.practice.enums.SensitiveEnum;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <p>
 * SensitiveWrapped
 * </p>
 *
 * @author jiabin.yu
 * @since 2024/7/15
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveSerialize.class)
public @interface SensitiveWrapped {

    /**
     * 脱敏类型
     * @return
     */
    SensitiveEnum value();
}

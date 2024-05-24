package com.jiabin.json.desensitize.practice.config;

/**
 * @author shiva   2022-09-17 23:06
 */
public interface DataMaskingOperation {
    String MASK_CHAR = "*";

    String mask(String content, String maskChar);
}

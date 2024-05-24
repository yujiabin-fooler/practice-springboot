package com.jiabin.json.desensitize.practice.config;

import com.jiabin.json.desensitize.practice.utils.CommonUtil;
import org.springframework.util.StringUtils;

/**
 * @author shiva   2022-09-17 23:06
 */

public enum DataMaskingFunc {

    /**
     *  脱敏转换器
     */
    NO_MASK((str, maskChar) -> {
        return str;
    }),
    NAME((str, maskChar) -> {
        return CommonUtil.nameDesensitized(str);
    }),
    IDCARD((str, maskChar) -> {
        return CommonUtil.idCardDesensitized(str);
    }),
    MOBILE((str, maskChar) -> {
        return CommonUtil.mobileDesensitized(str);
    }),
    ALL_MASK((str, maskChar) -> {
        if (StringUtils.hasLength(str)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                sb.append(StringUtils.hasLength(maskChar) ? maskChar : DataMaskingOperation.MASK_CHAR);
            }
            return sb.toString();
        } else {
            return str;
        }
    });

    private final DataMaskingOperation operation;

    private DataMaskingFunc(DataMaskingOperation operation) {
        this.operation = operation;
    }

    public DataMaskingOperation operation() {
        return this.operation;
    }

}

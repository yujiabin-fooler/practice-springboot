package com.jiabin.json.desensitize.practice.utils;

/**
 * @author shiva   2022-09-17 16:35
 */
public class CommonUtil {
    /**
     * 身份证号脱敏
     */
    public static String idCardDesensitized(String idCard) {
        //非正确号码，随意了
        if (idCard == null || idCard.length() < 18) {
            return idCard;
        }
        return idCard.substring(0, 4) + "******" + idCard.substring(idCard.length() - 6);
    }
    /**
     * 姓名脱敏
     */
    public static String nameDesensitized(String name) {
        if (name == null || "".equals(name)) {
            return name;
        }
        if (name.length() == 2) {
            return name.charAt(0) + "*";
        }
        if (name.length() == 3) {
            return name.charAt(0) + "*" + name.charAt(2);
        }
        if (name.length() == 4) {
            return name.charAt(0) + "**" + name.charAt(3);
        }
        return name;
    }
    /**
     * 手机号脱敏
     */
    public static String mobileDesensitized(String mobile) {
        if (mobile == null || "".equals(mobile) || mobile.length() != 11) {
            return mobile;
        }
        return mobile.substring(0, 3) + "******" + mobile.substring(mobile.length() - 4);
    }
}

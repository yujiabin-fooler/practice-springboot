package com.jiabin.jsonpath.practice.util;

import com.alibaba.fastjson.JSONPath;

import java.util.List;

public class JSONPathUtil {

    public static <T> T getByPath(String json, String path) {
        Object result = JSONPath.read(json, path);
        if (result instanceof List) {
            List<?> listResult = (List<?>) result;
            if (!listResult.isEmpty()) {
                return (T) listResult.get(0);
            }
        }
        return (T) result;
    }

    public static void main(String[] args) {
        String json = "[{\"title\":\"Title 1\",\"body\":\"Body 1\"},{\"title\":\"Title 2\",\"body\":\"Body 2\"}]";
        String title = getByPath(json, "$..title");
        String body = getByPath(json, "$..body");

        System.out.println("Title 值: " + title);
        System.out.println("Body 值: " + body);
    }
}
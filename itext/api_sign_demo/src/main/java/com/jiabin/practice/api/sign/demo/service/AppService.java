package com.jiabin.practice.api.sign.demo.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class AppService {

//    private static final Map<String, String> APP_INFO = Map.of("app1", "sign1", "app2", "sign2");
    private static  Map<String, String> APP_INFO = new HashMap<>();

    static {
        APP_INFO.put("app1", "sign1");
        APP_INFO.put("app2", "sign2");

    }

    public String getAppKey(String appId) {
        return APP_INFO.get(appId);
    }
}

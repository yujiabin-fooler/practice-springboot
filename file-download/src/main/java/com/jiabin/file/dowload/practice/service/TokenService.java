package com.jiabin.file.dowload.practice.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {
    private Map<String, String> tokenMap = new HashMap<>();

    public void storeToken(String fileName, String token) {
        tokenMap.put(fileName, token);
    }

    public String getToken(String fileName) {
        return tokenMap.get(fileName);
    }
}
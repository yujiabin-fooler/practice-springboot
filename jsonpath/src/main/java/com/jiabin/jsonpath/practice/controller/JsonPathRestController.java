package com.jiabin.jsonpath.practice.controller;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.jiabin.jsonpath.practice.util.JSONPathUtil;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class JsonPathRestController {

    @GetMapping
    public String getPosts() {
        // 发起HTTP请求获取JSON数据
        String json = HttpRequest.get("http://jsonplaceholder.typicode.com/posts").execute().body();

        // 使用JSONPath查询title和body的值
        String title = JSONPathUtil.getByPath(json, "$..title");
        String body = JSONPathUtil.getByPath(json, "$..body");

        // 构建返回结果
        ResultData resultData = new ResultData(title, body);

        // 将Java对象转换为JSON字符串
        return JSON.toJSONString(resultData);
    }

    // 用于封装返回结果的简单Java对象
    @Data
    private static class ResultData {
        private String title;
        private String body;

        public ResultData(String title, String body) {
            this.title = title;
            this.body = body;
        }

       
    }
}

package com.jiabin.activiti.practice.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jiabin.yu   2022/3/5 21:50
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Response {

    /**
     * 响应码
     */
    private int code;
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 响应业务数据
     */
    private Object data;

    public static Response error(String msg) {
        Response response = new Response();
        response.code = 1;
        response.msg = msg;
        return response;
    }

    public static Response error(String msg, int code) {
        Response response = new Response();
        response.code = code;
        response.msg = msg;
        return response;
    }

    public static Response success(String msg) {
        Response response = new Response();
        response.code = 0;
        response.msg = msg;
        return response;
    }
    public static Response success(String msg,Object data) {
        Response response = new Response();
        response.code = 0;
        response.msg = msg;
        response.data = data;
        return response;
    }
}

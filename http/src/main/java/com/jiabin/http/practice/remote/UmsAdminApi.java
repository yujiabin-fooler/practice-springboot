package com.jiabin.http.practice.remote;

import com.jiabin.http.practice.api.CommonResult;
import com.jiabin.http.practice.domain.LoginInfo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;


/**
 * 定义Http接口，用于调用远程的UmsAdmin服务
 * Created by macro on 2022/1/19.
 */
@HttpExchange
public interface UmsAdminApi {

    @PostExchange("admin/login")
    CommonResult<LoginInfo> login(@RequestParam("username") String username, @RequestParam("password") String password);
}

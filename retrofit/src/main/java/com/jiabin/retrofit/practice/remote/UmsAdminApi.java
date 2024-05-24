package com.jiabin.retrofit.practice.remote;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.jiabin.retrofit.practice.api.CommonResult;
import com.jiabin.retrofit.practice.domain.LoginInfo;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * 定义Http接口，用于调用远程的UmsAdmin服务
 * Created by macro on 2022/1/19.
 */
@RetrofitClient(baseUrl = "${remote.baseUrl}")
public interface UmsAdminApi {

    @FormUrlEncoded
    @POST("admin/login")
    CommonResult<LoginInfo> login(@Field("username") String username, @Field("password") String password);
}

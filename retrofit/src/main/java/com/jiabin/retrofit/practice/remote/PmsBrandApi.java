package com.jiabin.retrofit.practice.remote;

import com.github.lianjiatech.retrofit.spring.boot.annotation.Intercept;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.jiabin.retrofit.practice.api.CommonPage;
import com.jiabin.retrofit.practice.api.CommonResult;
import com.jiabin.retrofit.practice.component.TokenInterceptor;
import com.jiabin.retrofit.practice.domain.PmsBrand;
import retrofit2.http.*;

/**
 * 定义Http接口，用于调用远程的PmsBrand服务
 * Created by macro on 2022/1/19.
 */
@RetrofitClient(baseUrl = "${remote.baseUrl}")
@Intercept(handler = TokenInterceptor.class, include = "/brand/**")
public interface PmsBrandApi {
    @GET("brand/list")
    CommonResult<CommonPage<PmsBrand>> list(@Query("pageNum") Integer pageNum, @Query("pageSize") Integer pageSize);

    @GET("brand/{id}")
    CommonResult<PmsBrand> detail(@Path("id") Long id);

    @POST("brand/create")
    CommonResult create(@Body PmsBrand pmsBrand);

    @POST("brand/update/{id}")
    CommonResult update(@Path("id") Long id, @Body PmsBrand pmsBrand);

    @GET("brand/delete/{id}")
    CommonResult delete(@Path("id") Long id);
}

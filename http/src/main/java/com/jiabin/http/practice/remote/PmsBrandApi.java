package com.jiabin.http.practice.remote;

import com.jiabin.http.practice.api.CommonPage;
import com.jiabin.http.practice.api.CommonResult;
import com.jiabin.http.practice.domain.PmsBrand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * 定义Http接口，用于调用远程的PmsBrand服务
 * Created by macro on 2022/1/19.
 */
@HttpExchange
public interface PmsBrandApi {
    @GetExchange("brand/list")
    CommonResult<CommonPage<PmsBrand>> list(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);

    @GetExchange("brand/{id}")
    CommonResult<PmsBrand> detail(@PathVariable("id") Long id);

    @PostExchange("brand/create")
    CommonResult create(@RequestBody PmsBrand pmsBrand);

    @PostExchange("brand/update/{id}")
    CommonResult update(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrand);

    @GetExchange("brand/delete/{id}")
    CommonResult delete(@PathVariable("id") Long id);
}

package com.jiabin.http.practice.controller;

import com.jiabin.http.practice.api.CommonPage;
import com.jiabin.http.practice.api.CommonResult;
import com.jiabin.http.practice.component.TokenHolder;
import com.jiabin.http.practice.domain.LoginInfo;
import com.jiabin.http.practice.domain.PmsBrand;
import com.jiabin.http.practice.remote.PmsBrandApi;
import com.jiabin.http.practice.remote.UmsAdminApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * HttpInterface测试接口
 * @author jiabin.yu 2022/1/19.
 */
@RestController
@Api(tags = "HttpInterfaceController")
@Tag(name = "HttpInterfaceController", description = "HttpInterface测试接口")
@RequestMapping("/remote")
public class HttpInterfaceController {

    @Autowired
    private UmsAdminApi umsAdminApi;
    @Autowired
    private PmsBrandApi pmsBrandApi;
    @Autowired
    private TokenHolder tokenHolder;

    @ApiOperation(value = "调用远程登录接口获取token")
    @PostMapping(value = "/admin/login")
    public CommonResult<LoginInfo> login(@RequestParam String username, @RequestParam String password) {
        CommonResult<LoginInfo> result = umsAdminApi.login(username, password);
        LoginInfo loginInfo = result.getData();
        if (result.getData() != null) {
            tokenHolder.putToken(loginInfo.getTokenHead() + " " + loginInfo.getToken());
        }
        return result;
    }

    @ApiOperation("调用远程接口分页查询品牌列表")
    @GetMapping(value = "/brand/list")
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1")
                                                        @ApiParam("页码") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3")
                                                        @ApiParam("每页数量") Integer pageSize) {
        return pmsBrandApi.list(pageNum, pageSize);
    }

    @ApiOperation("调用远程接口获取指定id的品牌详情")
    @GetMapping(value = "/brand/{id}")
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
        return pmsBrandApi.detail(id);
    }

    @ApiOperation("调用远程接口添加品牌")
    @PostMapping(value = "/brand/create")
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        return pmsBrandApi.create(pmsBrand);
    }

    @ApiOperation("调用远程接口更新指定id品牌信息")
    @PostMapping(value = "/brand/update/{id}")
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrand) {
        return pmsBrandApi.update(id,pmsBrand);
    }

    @ApiOperation("调用远程接口删除指定id的品牌")
    @GetMapping(value = "/delete/{id}")
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        return  pmsBrandApi.delete(id);
    }
}

package com.jiabin.erupt.job.practice.controller;

import com.jiabin.erupt.job.practice.api.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jiabin.yu 2021/3/25.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/first", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult first() {
        String message = "返回消息";
        return CommonResult.success(null,message);
    }
}

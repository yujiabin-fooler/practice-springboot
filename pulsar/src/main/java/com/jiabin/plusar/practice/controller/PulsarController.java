package com.jiabin.plusar.practice.controller;

import com.jiabin.plusar.practice.api.CommonResult;
import com.jiabin.plusar.practice.component.PulsarProducer;
import com.jiabin.plusar.practice.dto.MessageDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Pulsar功能测试
 * @author jiabin.yu 2021/5/19.
 */
@Api(tags = "PulsarController", description = "Pulsar功能测试")
@Controller
@RequestMapping("/pulsar")
public class PulsarController {

    @Autowired
    private PulsarProducer pulsarProducer;

    @ApiOperation("发送消息")
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult sendMessage(@RequestBody MessageDto message) {
        pulsarProducer.send(message);
        return CommonResult.success(null);
    }
}

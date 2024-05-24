package com.jiabin.globeexception.practice.controller;

import com.jiabin.globeexception.practice.exception.BizException;
import com.jiabin.globeexception.practice.model.RequestVO;
import com.jiabin.globeexception.practice.model.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 参数校验及全局异常处理测试Controller
 */
@RestController
@RequestMapping("/ex")
@Validated
public class BusinessController {

    @GetMapping("/param_ex")
    public Result<RequestVO> paramEx(@Valid RequestVO request) {
        return Result.success();
    }

    @GetMapping("/param_in_query")
    public Result<String> paramInQuery(@NotBlank(message = "PARAM 不能为空") String param,
                                       @Min(value = 1, message = "number不能小于1") Integer number) {
        return Result.success();
    }

    @GetMapping("/biz_ex")
    public void biz() {
        throw new BizException("-1", "业务异常");
    }

    @GetMapping("/other_ex")
    public void other() {
        throw new RuntimeException("未定义的异常信息");
    }

}

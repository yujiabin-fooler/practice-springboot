package com.jiabin.annotation.validation.practice.web;

import com.jiabin.annotation.validation.practice.core.exception.CommonException;
import com.jiabin.annotation.validation.practice.core.result.ResultMsg;
import com.jiabin.annotation.validation.practice.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * UserController
 * </p>
 *
 * @author jiabin.yu
 * @since 2024/6/12
 */
@RestController
public class UserController {

    /**
     * 对象校验
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public ResultMsg register(@RequestBody @Valid User user){
        if(!user.getUserPwd().equals(user.getConfirmPwd())){
            throw new CommonException(4001, "确认密码与密码不相同，请确认！");
        }
        //业务处理...
        return ResultMsg.success();
    }

    /**
     * 请求参数校验
     * @param userId
     * @return
     */
    @RequestMapping("/query")
    public ResultMsg query(@RequestParam("userId") @Valid @NotBlank(message = "用户ID不能为空") String userId ){
        return ResultMsg.success();
    }
}

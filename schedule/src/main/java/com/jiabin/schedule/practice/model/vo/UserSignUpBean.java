package com.jiabin.schedule.practice.model.vo;

import com.jiabin.schedule.practice.model.BaseBean;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Description: 用户注册接收参数bean
 * @Author jiabin.yu
 * @Date: 2019/1/24
 */
@Data
public class UserSignUpBean extends BaseBean {

    private static final long serialVersionUID = 6970430558841356592L;

    /**
     * 账号
     */
    @NotNull(message = "api.response.code.user.accountNullError")
    @Pattern(regexp = "^\\S{5,50}$", message = "api.response.code.user.accountFormatError")
    private String userName;

    /**
     * 密码
     */
    @NotNull(message = "api.response.code.user.passwordNullError")
    @Pattern(regexp = "^\\w{5,80}$", message = "api.response.code.user.passwordFormatError")
    private String userPasscode;


}

package com.jiabin.globeexception.practice.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @createDate 2023-12-26 16:54
 */
@Data
public class RequestVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "用户名不能为空")
    private String username;


    @NotBlank(message = "手机号码不能为空")
    @Length(min = 11, max = 11, message = "手机号码格式错误")
    private String phoneNumber;
}

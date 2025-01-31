package com.jiabin.knife4j.practice.controller.two;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "实例演示2-Knife4j接口文档")
@RestController
@RequestMapping("/two")
public class TestKnife4jTwoController {

    @ApiOperation(value = "Knife4j接口文档演示")
    @GetMapping("/testKnife4j")
    public User testKnife4j(User param) {
        User user = new User();
        user.setName("笑小枫");
        user.setAge(18);
        user.setRemark("大家好，我是笑小枫，喜欢我的小伙伴点个赞呗，欢迎访问我的个人博客：https://www.xiaoxiaofeng.com");
        return user;
    }

    @Data
    @ApiModel("用户对象")
    static class User {
        @ApiModelProperty(value = "姓名")
        private String name;

        @ApiModelProperty(value = "年龄")
        private Integer age;

        @ApiModelProperty(value = "描述")
        private String remark;
    }
}

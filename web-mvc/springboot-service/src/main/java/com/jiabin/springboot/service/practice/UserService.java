package com.jiabin.springboot.service.practice;

import com.jiabin.springboot.base.practice.api.ApiResult;
import com.jiabin.springboot.base.practice.api.ApiResultI18n;
import com.jiabin.springboot.model.practice.vo.UserSignUpBean;

import java.util.Map;

/**
 * @Description: 用户模块业务
 * @Author: junqiang.lu
 * @Date: 2018/10/9
 */
public interface UserService {

    /**
     * 列表查询
     *
     * @param map 分页信息
     * @return
     */
    ApiResult queryList(Map<String, Object> map);

    /**
     * 用户注册
     *
     * @param userSignUpBean 用户注册信息
     * @return
     */
    ApiResultI18n signUp(UserSignUpBean userSignUpBean) throws Exception;

}

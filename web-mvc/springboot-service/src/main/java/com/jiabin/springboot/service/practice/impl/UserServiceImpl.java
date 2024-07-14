package com.jiabin.springboot.service.practice.impl;

import com.jiabin.springboot.base.practice.api.ApiResult;
import com.jiabin.springboot.base.practice.api.ApiResultI18n;
import com.jiabin.springboot.base.practice.api.ResponseCodeI18n;
import com.jiabin.springboot.base.practice.cache.RedisUtil;
import com.jiabin.springboot.dao.practice.user.UserDao;
import com.jiabin.springboot.model.practice.entity.UserDO;
import com.jiabin.springboot.service.practice.UserService;
import com.jiabin.springboot.model.practice.vo.UserSignUpBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 用户业务具体实现
 * @Author jiabin.yu
 * @Date: 2018/10/9
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 列表查询
     *
     * @param map 分页信息
     * @return
     */
    @Override
    public ApiResult queryList(Map<String, Object> map) {

        // TODO 分页数据处理
        List<Object> list = userDao.queryListComplex(map);
        redisUtil.set("userList", list, 60L);

        return ApiResult.success(userDao.queryListComplex(map));
    }

    /**
     * 用户注册
     *
     * @param userSignUpBean 用户注册信息
     * @return
     */
    @Override
    public ApiResultI18n signUp(UserSignUpBean userSignUpBean) throws Exception{
        /**
         * 参数校验顺序: 基本入参校验 --> 具体参数合法性校验(非数据库层校验) --> 数据库层参数校验
         */

        /**
         * 请求参数获取
         */
        UserDO userParams = new UserDO();
        BeanUtils.copyProperties(userSignUpBean, userParams);
        /**
         * 此处省略
         * 参数合法性校验校验... ...
         */

        // 注册帐号数据库层校验
        int userCount = userDao.signUpCheck(userParams);
        if (userCount > 0) {
            return ApiResultI18n.failure(ResponseCodeI18n.ACCOUNT_EXIST, userSignUpBean.getLanguage());
        }
        /**
         * 此处省略
         * 用户注册相关操作... ...
         */

        return ApiResultI18n.success(userSignUpBean.getLanguage());
    }


}

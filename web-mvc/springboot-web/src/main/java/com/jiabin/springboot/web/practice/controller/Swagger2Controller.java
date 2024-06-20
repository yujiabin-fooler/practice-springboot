package com.jiabin.springboot.web.practice.controller;

import com.jiabin.springboot.base.practice.api.ApiResult;
import com.jiabin.springboot.base.practice.api.ResponseCode;
import com.jiabin.springboot.base.practice.exception.ParamsCheckException;
import com.jiabin.springboot.service.practice.Swagger2Service;
import com.jiabin.springboot.model.practice.vo.swagger2.ModelAnnotationBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Swagger2 示例控制中心
 * @Author: junqiang.lu
 * @Date: 2019/3/23
 */
@RestController
@RequestMapping(value = "api/swagger2")
@Slf4j
@Api(value = "Swagger 2 控制层", tags = "Swagger 2 控制层")
public class Swagger2Controller {

    @Autowired
    private Swagger2Service swagger2Service;

    /**
     * 实体类注解测试
     *
     * @param modelAnnotationBean
     * @return
     */
    @RequestMapping(value = "modelAnnotation", method = RequestMethod.POST)
    @ApiOperation(value = "Swagger 2 注解示范",notes = "Swagger 2 注解示范")
    public ApiResult modelAnnotation(@RequestBody ModelAnnotationBean modelAnnotationBean) {

        ApiResult apiResult = null;
        try {
            apiResult = swagger2Service.modelAnnotation(modelAnnotationBean);
        } catch (Exception e) {
            if (ParamsCheckException.class.isAssignableFrom(e.getClass())){
                log.error("参数错误");
                return apiResult.failure(ResponseCode.PARAM_ERROR);
            }
            log.error("未知异常",e);
            return apiResult.failure(ResponseCode.UNKNOWN_ERROR);
        }

        return apiResult;
    }


}

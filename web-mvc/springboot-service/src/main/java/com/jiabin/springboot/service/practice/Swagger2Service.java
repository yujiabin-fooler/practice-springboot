package com.jiabin.springboot.service.practice;

import com.jiabin.springboot.base.practice.api.ApiResult;
import com.jiabin.springboot.model.practice.vo.swagger2.ModelAnnotationBean;

/**
 * @Description: Swagger2 业务接口
 * @Author jiabin.yu
 * @Date: 2019/3/23
 */
public interface Swagger2Service {

    /**
     * Swagger2 参数接受实体类注解@ApiModel...示例
     *
     * @param modelAnnotationBean
     * @return
     * @throws Exception
     */
    ApiResult modelAnnotation(ModelAnnotationBean modelAnnotationBean) throws Exception;

}

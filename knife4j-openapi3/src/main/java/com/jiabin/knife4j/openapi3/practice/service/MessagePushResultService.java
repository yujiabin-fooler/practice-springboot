package com.jiabin.knife4j.openapi3.practice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiabin.knife4j.openapi3.practice.common.api.ApiResult;
import com.jiabin.knife4j.openapi3.practice.model.entity.MessagePushResultEntity;
import com.jiabin.knife4j.openapi3.practice.model.param.messagepushresult.*;

/**
 * 消息推送结果业务层接口
 * 
 * @author junqiang.lu
 * @date 2023-08-15 10:41:29
 */
public interface MessagePushResultService extends IService<MessagePushResultEntity> {

	/**
     * 保存(单条)
     *
     * @param saveParam
     * @return
     */
	ApiResult save(MessagePushResultSaveParam saveParam);

	/**
     * 查询详情(单条)
     *
     * @param infoParam
     * @return
     */
	ApiResult info(MessagePushResultInfoParam infoParam);

	/**
     * 查询列表
     *
     * @param listParam
     * @return
     */
	ApiResult list(MessagePushResultListParam listParam);

	/**
     * 更新(单条)
     *
     * @param updateParam
     * @return
     */
	ApiResult update(MessagePushResultUpdateParam updateParam);

	/**
     * 删除(单条)
     *
     * @param deleteParam
     * @return
     */
	ApiResult delete(MessagePushResultDeleteParam deleteParam);


}

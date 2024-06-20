package com.jiabin.knife4j.openapi3.practice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiabin.knife4j.openapi3.practice.common.api.ApiResult;
import com.jiabin.knife4j.openapi3.practice.model.BasePageParam;
import com.jiabin.knife4j.openapi3.practice.model.entity.UserMessageEntity;
import com.jiabin.knife4j.openapi3.practice.model.param.messagepush.MessagePushParam;
import com.jiabin.knife4j.openapi3.practice.model.param.usermessage.*;

/**
 * 用户消息业务层接口
 * 
 * @author junqiang.lu
 * @date 2023-08-15 10:41:29
 */
public interface UserMessageService extends IService<UserMessageEntity> {

	/**
     * 保存(单条)
     *
     * @param saveParam
     * @return
     */
	ApiResult save(UserMessageSaveParam saveParam);

	/**
     * 查询详情(单条)
     *
     * @param infoParam
     * @return
     */
	ApiResult info(UserMessageInfoParam infoParam);

	/**
     * 查询列表
     *
     * @param listParam
     * @return
     */
	ApiResult list(UserMessageListParam listParam);

	/**
     * 更新(单条)
     *
     * @param updateParam
     * @return
     */
	ApiResult update(UserMessageUpdateParam updateParam);

	/**
     * 删除(单条)
     *
     * @param deleteParam
     * @return
     */
	ApiResult delete(UserMessageDeleteParam deleteParam);

	/**
	 * 查询未推送成功的消息
	 *
	 * @param pageParam
	 * @return
	 */
	IPage<UserMessageEntity> queryPageFailMessage(BasePageParam pageParam);

	/**
	 * 推送消息
	 *
	 * @param pushParam
	 * @return
	 */
	ApiResult push(MessagePushParam pushParam);

	/**
	 * 重新推送消息
	 *
	 * @param userMessage
	 * @return
	 */
	void repush(UserMessageEntity userMessage);



}

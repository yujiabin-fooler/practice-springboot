package com.jiabin.knife4j.openapi3.practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiabin.knife4j.openapi3.practice.model.entity.MessagePushResultEntity;
import org.springframework.stereotype.Repository;

/**
 * 消息推送结果
 * 
 * @author junqiang.lu
 * @date 2023-08-14 18:06:40
 */
@Repository
public interface MessagePushResultMapper extends BaseMapper<MessagePushResultEntity> {
	
}

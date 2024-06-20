package com.jiabin.springboot.dao.practice.restuser;

import com.jiabin.springboot.dao.practice.BaseDao;
import com.jiabin.springboot.model.practice.entity.RestUserEntity;
import org.springframework.stereotype.Repository;

/**
 * REST示例-用户表
 * 
 * @author junqiang.lu
 * @date 2019-09-19 17:19:42
 */
@Repository
public interface RestUserDao extends BaseDao<RestUserEntity> {
	
}

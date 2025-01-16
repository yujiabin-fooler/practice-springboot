package com.jiabin.encrypt.filed.likequery.domain;

import org.springframework.util.StringUtils;

import com.jiabin.encrypt.filed.likequery.utils.LikeQueryUtils;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;

public class UserListener {

	@PostPersist
	@PostUpdate
	public void onUpdate(User user) {
		String phone = user.getPhone() ;
		String idNo = user.getIdNo() ;
		if (StringUtils.hasLength(phone)) {
		  user.setKeyLikePhone(LikeQueryUtils.encrypt(phone)) ;
		}
		if (StringUtils.hasLength(idNo)) {
		  user.setKeyLikeIdNo(LikeQueryUtils.encrypt(idNo)) ;
		}
	}
}

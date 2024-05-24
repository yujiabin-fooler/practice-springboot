package com.jiabin.wechat.mp.practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiabin.wechat.mp.practice.entity.RmUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface RmUserMapper extends BaseMapper<RmUser> {
	
	@Select({
		"<script>",
		"SELECT "
		+" * "
		+ " from rm_user WHERE `username`=#{arg0} ",
		"</script>"
	})
	RmUser findByUsername(String username);
}
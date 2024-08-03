package com.jiabin.menu.role.practice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiabin.menu.role.practice.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author jiabin.yu
 * @since 2020-06-28
 */
public interface RoleMapper extends BaseMapper<Role> {


    List<Role> findAll();

}

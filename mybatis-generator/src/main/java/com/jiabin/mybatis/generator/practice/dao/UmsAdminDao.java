package com.jiabin.mybatis.generator.practice.dao;

import com.jiabin.mybatis.generator.practice.domain.AdminRoleDto;
import com.jiabin.mybatis.generator.practice.domain.ResourceWithCateDto;
import com.jiabin.mybatis.generator.practice.domain.RoleStatDto;
import com.jiabin.mybatis.generator.practice.mbg.model.UmsAdmin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by macro on 2020/12/9.
 */
public interface UmsAdminDao {

    List<RoleStatDto> groupList();

    AdminRoleDto selectWithRoleList(@Param("id") Long id);

    List<UmsAdmin> subList(@Param("roleId") Long roleId);

    ResourceWithCateDto selectResourceWithCate(@Param("id")Long id);
}

package com.jiabin.dynamic.sql.practice.service;

import com.jiabin.dynamic.sql.practice.domain.AdminRoleDto;
import com.jiabin.dynamic.sql.practice.domain.RoleStatDto;
import com.jiabin.dynamic.sql.practice.mbg.model.UmsAdmin;

import java.util.List;

/**
 * 后台用户管理Service
 * @author jiabin.yu 2020/12/8.
 */
public interface UmsAdminService {
    void create(UmsAdmin entity);

    void update(UmsAdmin entity);

    void delete(Long id);

    UmsAdmin select(Long id);

    List<UmsAdmin> listAll(Integer pageNum, Integer pageSize);

    List<UmsAdmin> list(Integer pageNum, Integer pageSize, String username, List<Integer> statusList);

    List<UmsAdmin> lambdaList(Integer pageNum, Integer pageSize, String username, List<Integer> statusList);

    List<UmsAdmin> subList(Long roleId);

    List<RoleStatDto> groupList();

    void deleteByUsername(String username);

    void updateByIds(List<Long> ids,Integer status);

    AdminRoleDto selectWithRoleList(Long id);
}

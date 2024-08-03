package com.jiabin.menu.role.practice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiabin.menu.role.practice.entity.Role;
import com.jiabin.menu.role.practice.entity.dto.RoleDTO;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author jiabin.yu
 * @since 2020-06-28
 */
public interface RoleService extends IService<Role> {

    List<Role> findAll();

    boolean add(RoleDTO roleDTO);

    boolean delete(RoleDTO roleDTO);

}

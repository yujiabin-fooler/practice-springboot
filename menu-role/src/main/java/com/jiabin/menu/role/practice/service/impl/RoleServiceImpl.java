package com.jiabin.menu.role.practice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiabin.menu.role.practice.dao.RoleMapper;
import com.jiabin.menu.role.practice.entity.Role;
import com.jiabin.menu.role.practice.entity.dto.RoleDTO;
import com.jiabin.menu.role.practice.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author jiabin.yu
 * @since 2020-06-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


    @Override
    public List<Role> findAll() {
        return super.baseMapper.findAll();
    }

    @Override
    public boolean add(RoleDTO roleDTO) {
        // todo...
        return false;
    }

    @Override
    public boolean delete(RoleDTO roleDTO) {
        // todo...
        return false;
    }
}

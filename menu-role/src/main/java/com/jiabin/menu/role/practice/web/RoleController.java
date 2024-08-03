package com.jiabin.menu.role.practice.web;


import com.jiabin.menu.role.practice.config.aspect.CheckPermissions;
import com.jiabin.menu.role.practice.entity.Role;
import com.jiabin.menu.role.practice.entity.dto.RoleDTO;
import com.jiabin.menu.role.practice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author jiabin.yu
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @CheckPermissions(value="roleMgr:list")
    @PostMapping(value = "/queryRole")
    public List<Role> queryRole(RoleDTO roleDTO){
        return roleService.findAll();
    }

    @CheckPermissions(value="roleMgr:add")
    @PostMapping(value = "/addRole")
    public void addRole(RoleDTO roleDTO){
        roleService.add(roleDTO);
    }

    @CheckPermissions(value="roleMgr:delete")
    @PostMapping(value = "/deleteRole")
    public void deleteRole(RoleDTO roleDTO){
        roleService.delete(roleDTO);
    }
}

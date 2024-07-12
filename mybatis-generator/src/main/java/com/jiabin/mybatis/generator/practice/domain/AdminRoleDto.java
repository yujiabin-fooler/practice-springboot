package com.jiabin.mybatis.generator.practice.domain;

import com.jiabin.mybatis.generator.practice.mbg.model.UmsAdmin;
import com.jiabin.mybatis.generator.practice.mbg.model.UmsRole;

import java.util.List;

/**
 * @author jiabin.yu 2020/12/9.
 */
public class AdminRoleDto extends UmsAdmin {

    private List<UmsRole> roleList;

    public List<UmsRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<UmsRole> roleList) {
        this.roleList = roleList;
    }
}

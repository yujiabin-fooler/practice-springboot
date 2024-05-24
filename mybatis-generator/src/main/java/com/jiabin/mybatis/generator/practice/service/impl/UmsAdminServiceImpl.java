package com.jiabin.mybatis.generator.practice.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.jiabin.mybatis.generator.practice.dao.UmsAdminDao;
import com.jiabin.mybatis.generator.practice.domain.AdminRoleDto;
import com.jiabin.mybatis.generator.practice.domain.ResourceWithCateDto;
import com.jiabin.mybatis.generator.practice.domain.RoleStatDto;
import com.jiabin.mybatis.generator.practice.mbg.mapper.UmsAdminMapper;
import com.jiabin.mybatis.generator.practice.mbg.model.UmsAdmin;
import com.jiabin.mybatis.generator.practice.mbg.model.UmsAdminExample;
import com.jiabin.mybatis.generator.practice.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 后台用户管理Service实现类
 * Created by macro on 2020/12/8.
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private UmsAdminDao adminDao;

    @Override
    public void create(UmsAdmin entity) {
        adminMapper.insert(entity);
    }

    @Override
    public void update(UmsAdmin entity) {
        adminMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public void delete(Long id) {
        adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UmsAdmin select(Long id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UmsAdmin> listAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminMapper.selectByExample(new UmsAdminExample());
    }

    @Override
    public List<UmsAdmin> list(Integer pageNum, Integer pageSize, String username, List<Integer> statusList) {
        PageHelper.startPage(pageNum, pageSize);
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        UmsAdminExample.Criteria criteria = umsAdminExample.createCriteria();
        if(StrUtil.isNotEmpty(username)){
            criteria.andUsernameEqualTo(username);
        }
        criteria.andStatusIn(statusList);
        umsAdminExample.setOrderByClause("create_time desc");
        return adminMapper.selectByExample(umsAdminExample);
    }

    @Override
    public List<UmsAdmin> subList(Long roleId) {
        return adminDao.subList(roleId);
    }

    @Override
    public List<RoleStatDto> groupList() {
        return adminDao.groupList();
    }

    @Override
    public void deleteByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        adminMapper.deleteByExample(example);
    }

    @Override
    public void updateByIds(List<Long> ids, Integer status) {
        UmsAdmin record = new UmsAdmin();
        record.setStatus(status);
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andIdIn(ids);
        adminMapper.updateByExampleSelective(record,example);
    }

    @Override
    public AdminRoleDto selectWithRoleList(Long id) {
        return adminDao.selectWithRoleList(id);
    }

    @Override
    public ResourceWithCateDto selectResourceWithCate(Long id) {
        return adminDao.selectResourceWithCate(id);
    }
}

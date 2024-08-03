package com.jiabin.menu.role.practice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiabin.menu.role.practice.entity.Menu;
import com.jiabin.menu.role.practice.entity.vo.MenuVo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author jiabin.yu
 * @since 2020-06-28
 */
public interface MenuService extends IService<Menu> {

    void addMenu(Menu menu);

    List<MenuVo> queryMenuTree();

    List<MenuVo> queryMenus(Long userId);
}

package com.jiabin.menu.role.practice.web;


import com.jiabin.menu.role.practice.entity.dto.BaseDTO;
import com.jiabin.menu.role.practice.entity.vo.MenuVo;
import com.jiabin.menu.role.practice.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author jiabin.yu
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 增加权限控制
     * @param baseDTO
     * @return
     */
    @PostMapping(value = "/queryMenuTree")
    public List<MenuVo> queryTreeMenu(BaseDTO baseDTO){
        return menuService.queryMenuTree();
    }


    @PostMapping(value = "/queryMenus")
    public List<MenuVo> queryMenus(BaseDTO request){
        return menuService.queryMenus(request.getLoginUserId());
    }
}

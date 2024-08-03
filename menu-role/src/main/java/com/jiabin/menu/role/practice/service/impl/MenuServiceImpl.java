package com.jiabin.menu.role.practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiabin.menu.role.practice.config.exception.CommonException;
import com.jiabin.menu.role.practice.dao.MenuMapper;
import com.jiabin.menu.role.practice.entity.Menu;
import com.jiabin.menu.role.practice.entity.RoleMenu;
import com.jiabin.menu.role.practice.entity.UserRole;
import com.jiabin.menu.role.practice.entity.vo.MenuVo;
import com.jiabin.menu.role.practice.service.MenuService;
import com.jiabin.menu.role.practice.service.RoleMenuService;
import com.jiabin.menu.role.practice.service.UserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author jiabin.yu
 * @since 2020-06-28
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public void addMenu(Menu menu) {
        //如果插入的当前节点为根节点，parentId指定为0
        if(menu.getParentId().longValue() == 0){
            menu.setLevel(1);//根节点层级为1
            menu.setPath(null);//根节点路径为空
        }else{
            Menu parentMenu = baseMapper.selectById(menu.getParentId());
            if(parentMenu == null){
                throw new CommonException("未查询到对应的父节点");
            }
            menu.setLevel(parentMenu.getLevel().intValue() + 1);
            if(StringUtils.isNotEmpty(parentMenu.getPath())){
                menu.setPath(parentMenu.getPath() + "," + parentMenu.getId());
            }else{
                menu.setPath(parentMenu.getId().toString());
            }
        }
        //可以使用雪花算法，生成ID
        menu.setId(System.currentTimeMillis());
        super.save(menu);
    }

    @Override
    public List<MenuVo> queryMenuTree() {
        Wrapper queryObj = new QueryWrapper<>().orderByAsc("level","sort");
        List<Menu> allMenu = super.list(queryObj);
        List<MenuVo> resultList = transferMenuVo(allMenu, 0L);
        return resultList;
    }

    @Override
    public List<MenuVo> queryMenus(Long userId) {
        //1、先查询当前用户对应的角色
        Wrapper queryUserRoleObj = new QueryWrapper<>().eq("user_id", userId);
        List<UserRole> userRoles = userRoleService.list(queryUserRoleObj);
        if(!CollectionUtils.isEmpty(userRoles)){
            //2、通过角色查询菜单（默认取第一个角色）
            Wrapper queryRoleMenuObj = new QueryWrapper<>().eq("role_id", userRoles.get(0).getRoleId());
            List<RoleMenu> roleMenus = roleMenuService.list(queryRoleMenuObj);
            if(!CollectionUtils.isEmpty(roleMenus)){
                Set<Long> menuIds = new HashSet<>();
                for (RoleMenu roleMenu : roleMenus) {
                    menuIds.add(roleMenu.getMenuId());
                }
                //查询对应的菜单
                Wrapper queryMenuObj = new QueryWrapper<>().in("id", new ArrayList<>(menuIds));
                List<Menu> menus = super.list(queryMenuObj);
                if(!CollectionUtils.isEmpty(menus)){
                    //将菜单下对应的父节点也一并全部查询出来
                    Set<Long> allMenuIds = new HashSet<>();
                    for (Menu menu : menus) {
                        allMenuIds.add(menu.getId());
                        if(StringUtils.isNotEmpty(menu.getPath())){
                            String[] pathIds = StringUtils.split(menu.getPath(), ",");
                            for (String pathId : pathIds) {
                                allMenuIds.add(Long.valueOf(pathId));
                            }
                        }
                    }
                    //查询对应的所有菜单,并进行封装展示
                    List<Menu> allMenus = super.list(new QueryWrapper<Menu>().in("id", new ArrayList<>(allMenuIds)));
                    List<MenuVo> resultList = transferMenuVo(allMenus, 0L);
                    return resultList;
                }
            }

        }
        return null;
    }

    /**
     * 封装菜单视图
     * @param allMenu
     * @param parentId
     * @return
     */
    private List<MenuVo> transferMenuVo(List<Menu> allMenu, Long parentId){
        List<MenuVo> resultList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(allMenu)){
            for (Menu source : allMenu) {
                if(parentId.longValue() == source.getParentId().longValue()){
                    MenuVo menuVo = new MenuVo();
                    BeanUtils.copyProperties(source, menuVo);
                    //查询子菜单，并封装
                    List<MenuVo> childList = transferMenuVo(allMenu, source.getId());
                    if(!CollectionUtils.isEmpty(childList)){
                        menuVo.setChildMenu(childList);
                    }
                    resultList.add(menuVo);
                }
            }
        }
        return resultList;
    }


}

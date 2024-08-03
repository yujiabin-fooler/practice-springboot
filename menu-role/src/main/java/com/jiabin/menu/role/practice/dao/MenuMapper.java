package com.jiabin.menu.role.practice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiabin.menu.role.practice.entity.Menu;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author jiabin.yu
 * @since 2020-06-28
 */
public interface MenuMapper extends BaseMapper<Menu> {

    int selectAuthByUserIdAndMenuCode(@Param("userId") Long userId, @Param("menuCode") String menuCode);

}

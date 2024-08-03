package com.jiabin.menu.role.practice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author jiabin.yu
 * @since 2020-06-28
 */
@TableName("tb_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("id")
    private Long id;

    /**
     * 名称
     */
    @TableField("menu_name")
    private String name;

    /**
     * 菜单编码
     */
    @TableField("menu_code")
    private String menuCode;

    /**
     * 父节点
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 节点类型，1文件夹，2页面，3按钮
     */
    @TableField("node_type")
    private Integer nodeType;

    /**
     * 图标地址
     */
    @TableField("icon_url")
    private String iconUrl;

    /**
     * 排序号
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 页面对应的地址
     */
    @TableField("link_url")
    private String linkUrl;

    /**
     * 层次
     */
    @TableField("level")
    private Integer level;

    /**
     * 树id的路径 整个层次上的路径id，逗号分隔，想要找父节点特别快
     */
    @TableField("path")
    private String path;

    /**
     * 是否删除 1：已删除；0：未删除
     */
    @TableField("is_delete")
    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}

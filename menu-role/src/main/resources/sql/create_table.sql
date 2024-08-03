CREATE DATABASE IF NOT EXISTS menu_auth_db default charset utf8mb4 COLLATE utf8mb4_unicode_ci;


CREATE TABLE menu_auth_db.tb_user (
  id bigint(20) unsigned NOT NULL COMMENT '消息给过来的ID',
  mobile varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
  user_name varchar(100) NOT NULL DEFAULT '' COMMENT '姓名',
  password varchar(128) NOT NULL DEFAULT '' COMMENT '密码',
  is_delete tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 1：已删除；0：未删除',
  PRIMARY KEY (id),
  KEY idx_name (user_name) USING BTREE,
  KEY idx_mobile (mobile) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

CREATE TABLE menu_auth_db.tb_user_role (
  id bigint(20) unsigned NOT NULL COMMENT '主键',
  user_id bigint(20) NOT NULL COMMENT '用户ID',
  role_id bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (id),
  KEY idx_user_id (user_id) USING BTREE,
  KEY idx_role_id (role_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色表';

CREATE TABLE menu_auth_db.tb_role (
  id bigint(20) unsigned NOT NULL COMMENT '主键',
  code varchar(100) NOT NULL DEFAULT '' COMMENT '编码',
  role_name varchar(100) NOT NULL DEFAULT '' COMMENT '名称',
  is_delete tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 1：已删除；0：未删除',
  PRIMARY KEY (id),
  KEY idx_code (code) USING BTREE,
  KEY idx_name (role_name) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';


CREATE TABLE menu_auth_db.tb_role_menu (
  id bigint(20) unsigned NOT NULL COMMENT '主键',
  role_id bigint(20) NOT NULL COMMENT '角色ID',
  menu_id bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (id),
  KEY idx_role_id (role_id) USING BTREE,
  KEY idx_menu_id (menu_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色菜单关系表';


CREATE TABLE menu_auth_db.tb_menu (
  id bigint(20) NOT NULL COMMENT '主键',
  menu_name varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '名称',
  menu_code varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '菜单编码',
  parent_id bigint(20) DEFAULT NULL COMMENT '父节点',
  node_type tinyint(4) NOT NULL DEFAULT '1' COMMENT '节点类型，1文件夹，2页面，3按钮',
  icon_url varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '图标地址',
  sort int(11) NOT NULL DEFAULT '1' COMMENT '排序号',
  link_url varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '页面对应的地址',
  level int(11) NOT NULL DEFAULT '0' COMMENT '层次',
  path varchar(2500) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '树id的路径 整个层次上的路径id，逗号分隔，想要找父节点特别快',
  is_delete tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 1：已删除；0：未删除',
  PRIMARY KEY (id) USING BTREE,
  KEY idx_parent_id (parent_id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单表';
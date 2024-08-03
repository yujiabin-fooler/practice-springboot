INSERT INTO `tb_menu` (`id`, `menu_name`, `menu_code`, `parent_id`, `node_type`, `icon_url`, `sort`, `link_url`, `level`, `path`, `is_delete`)
VALUES
	(1593333386337, '角色管理', 'roleMgr', 0, 1, '', 1, '', 0, '', 0),
	(1719569945557, '角色管理-查询', 'roleMgr:list', 1593333386337, 3, '', 1, '', 1, '1593333386337', 0),
	(1719569945692, '角色管理-新增', 'roleMgr:add', 1593333386337, 3, '', 2, '', 1, '1593333386337', 0),
	(1719569945777, '角色管理-删除', 'roleMgr:delete', 1593333386337, 3, '', 3, '', 1, '1593333386337', 0);


INSERT INTO `tb_role` (`id`, `code`, `role_name`, `is_delete`)
VALUES
	(1, 'devop', '开发', 0);


INSERT INTO `tb_role_menu` (`id`, `role_id`, `menu_id`)
VALUES
	(1, 1, 1719569945557);


INSERT INTO `tb_user` (`id`, `mobile`, `user_name`, `password`, `is_delete`)
VALUES
	(1, '188888', '张三', '1', 0);


INSERT INTO `tb_user_role` (`id`, `user_id`, `role_id`)
VALUES
	(1, 1, 1);

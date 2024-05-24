DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) DEFAULT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名',
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名',
    `email` varchar(24) DEFAULT NULL COMMENT '邮箱' ,
     `mobile` varchar(24) DEFAULT NULL COMMENT '手机' ,
      `phone` varchar(24) DEFAULT NULL COMMENT '电话' ,
  `state` tinyint(4) DEFAULT NULL COMMENT '状态',
  `isdel` int(11) DEFAULT NULL COMMENT '是否删除',
  `remark` text COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `money` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `left_money` float DEFAULT NULL COMMENT '剩下的钱',
  `age` int(11) DEFAULT NULL,
  `password` varchar(24) DEFAULT NULL COMMENT '密码'
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1','wangwu', '王五', '1411@qq.com','13971314133','13224444','0', '0', '批量修改备注', '2017-02-21 10:37:44', '101.10', '22.1', null, null);
INSERT INTO `t_user` VALUES ('2', 'zhangsan','张三','1232@qq.com','13211232123','12333332', '0', '0', '批量修改备注', '2017-02-21 10:40:11', '100.50', '22.1', null, null);
INSERT INTO `t_user` VALUES ('3','zhangsan', '张三','123@qq.com', '13211232123','12333332','1', '0', '备注', '2017-02-21 10:40:11', '100.50', '22.1', null, null);
INSERT INTO `t_user` VALUES ('4','zhangsan', '张三', 'dsd@org.cn','13211232123','12333332','0', '0', '批量修改备注', '2017-02-21 10:40:11', '100.50', '22.1', null, null);
INSERT INTO `t_user` VALUES ('5', 'zhangsan','张三','2332@qq.com','13211232123','12333332', '0', '0', '批量修改备注', '2017-02-21 10:40:11', '100.50', '22.1', null, null);
INSERT INTO `t_user` VALUES ('6', 'zhangsan','张三','233323@org.cn','13211232123','12333332', '0', '0', '批量修改备注', null, '100.50', '22.1', null, null);
INSERT INTO `t_user` VALUES ('7', 'laoliu','老六','56789@qq.com','13712345678','13211212123', '0', '0', '批量修改备注', null, '100.50', '22.1', null, null);
INSERT INTO `t_user` VALUES ('8', 'xiaomin','晓民','123456789@qq.com','13211222143','12333332', '0', '0', '批量修改备注', null, '100.50', '22.1', null, null);

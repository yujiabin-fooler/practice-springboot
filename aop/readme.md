
code reference

spring-boot-route（十七）使用aop记录操作日志 https://www.cnblogs.com/zhixie/p/13830308.html



CREATE TABLE `sys_oper_log` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
`title` varchar(50) CHARACTER SET utf8 DEFAULT '' COMMENT '模块标题',
`business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
`method` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '方法名称',
`status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
`error_msg` varchar(2000) CHARACTER SET utf8 DEFAULT '' COMMENT '错误消息',
`oper_time` datetime DEFAULT NULL COMMENT '操作时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4 CHECKSUM=1 COMMENT='操作日志记录'
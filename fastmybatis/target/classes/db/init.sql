
CREATE TABLE IF NOT EXISTS `t_user`
(
    `id`        INTEGER PRIMARY KEY auto_increment,
    `username` varchar(255) DEFAULT NULL COMMENT '用户名',
    `state` tinyint(4) DEFAULT NULL COMMENT '状态',
    `isdel` int(11) DEFAULT NULL COMMENT '是否删除',
    `remark` text COMMENT '备注',
    `add_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    `money` decimal(10,2) DEFAULT NULL COMMENT '金额',
    `left_money` float DEFAULT NULL COMMENT '剩下的钱'
    );

INSERT  INTO `t_user`(`id`,`username`,`state`,`isdel`,`remark`,`add_time`,`money`,`left_money`)
VALUES (1,'王五',0,0,'批量修改备注','2017-02-21 10:37:44','101.10',22.1)
     ,(2,'张三',0,0,'批量修改备注','2017-02-21 10:40:11','100.50',22.1)
     ,(3,'张三',1,0,'备注','2017-02-21 10:40:11','100.50',22.1)
     ,(4,'张三',0,0,'批量修改备注','2017-02-21 10:40:11','100.50',22.1)
     ,(5,'张三',0,0,'批量修改备注','2017-02-21 10:40:11','100.50',22.1)
     ,(6,'张三',0,0,'批量修改备注',null,'100.50',22.1)
     ,(7,'13712345678',0,0,'批量修改备注',null,'100.50',22.1)
     ,(8,'123456789@qq.com',0,0,'批量修改备注',null,'100.50',22.1)
;



CREATE TABLE IF NOT EXISTS `student`
(
    `id`        INTEGER PRIMARY KEY auto_increment,
    `firstname` varchar(64) DEFAULT NULL COMMENT '名',
    `lastname` varchar(64) DEFAULT NULL COMMENT '姓',
    `age` int default null COMMENT '年龄',
    `active` tinyint(4) DEFAULT NULL COMMENT '状态',
    `start_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '开始日期'
    );

INSERT  INTO `student`
VALUES (1,'Jim', 'Green', 22, 1, '2018-02-21 10:37:00'),
       (2,'三', '张', 30, 1, '2019-11-21 11:37:44'),
       (3,'四', '张', 40, 1, '2017-12-21 12:55:00'),
       (4,'五一', '李', 40, 1, null),
       (5,'六', '张', null, 0, '2016-07-21 10:44:00');




CREATE TABLE `address` (
                           `id`        varchar(100) PRIMARY KEY,
                           `address` varchar(200) DEFAULT NULL
);

insert  into `address`(`id`,`address`) values ('1','aaaa'),('2','bbbb'),('3','vvv'),('4','3333'),('50830c82-8ede-11e7-8bd2-54e1ad3fb014','address。。');


CREATE TABLE user (
  id bigint(20) NOT NULL COMMENT '用户ID',
  email varchar(255)  NOT NULL DEFAULT '' COMMENT '邮件',
  nick_name varchar(255)  DEFAULT NULL COMMENT '昵称',
  pass_word varchar(255)  NOT NULL DEFAULT '' COMMENT '二次密码',
  reg_time varchar(255)  NOT NULL DEFAULT '' COMMENT '注册时间',
  user_name varchar(255)  NOT NULL DEFAULT '' COMMENT '用户名',
  salary varchar(255) DEFAULT NULL COMMENT '基本工资',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


code reference

Spring Boot整合MyBatis Plus配置多数据源
https://mp.weixin.qq.com/s/e6g0K6n9Uu8GdSOqu6iNwg


create table user_info(
id int(11) primary key not null AUTO_INCREMENT,
usercode varchar(20),
username varchar(20)
);


insert into user_info(id,usercode,username) values (1,"1101", '张三');



多数据源原理
![img.png](img.png)
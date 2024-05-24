package com.jiabin.fastmybatis.practice.mapper;

import com.gitee.fastmybatis.core.mapper.CrudMapper;
import com.jiabin.fastmybatis.practice.model.TUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TUserMapper extends CrudMapper<TUser, Integer> {

    // 自定义sql
    @Update("update t_user set username = #{username} where id = #{id}")
    int updateById(@Param("id") int id, @Param("username") String username);

    // 自定义SQL
    TUser selectByName(@Param("username") String username);

    // JPA findBy查询，无需在xml中写sql
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
    TUser findById(int id);

    TUser findByIdIs(int id);

    TUser findByIdEquals(int id);

    List<TUser> findByIdLessThan(int id);

    List<TUser> findByIdLessThanEqual(int id);

    List<TUser> findByIdGreaterThan(int id);

    List<TUser> findByIdGreaterThanEqual(int id);

    List<TUser> findByIdAfter(int id);

    List<TUser> findByIdBefore(int id);

    List<TUser> findByUsernameAndAddTimeNull(String username);

    List<TUser> findByUsernameAndAddTimeIsNull(String username);

    List<TUser> findByUsernameAndAddTimeNotNull(String username);

    List<TUser> findByUsernameAndAddTimeIsNotNull(String username);

    List<TUser> findByUsernameLike(String username);

    List<TUser> findByUsernameStartingWith(String username);

    List<TUser> findByUsernameEndingWith(String username);

    List<TUser> findByUsernameContaining(String username);

    List<TUser> findByUsernameOrderByIdDescAddTimeAsc(String username);

    List<TUser> findByIdOrUsernameEndingWithOrRemarkStartingWith(int id, String username, String remark);

    List<TUser> findByIdIn(List<Integer> ids);

    List<TUser> findByIdNotIn(List<Integer> ids);

    List<TUser> findByUsernameNot(String username);

    List<TUser> findByUsernameAndState(String username, byte state);

    List<TUser> findByUsernameIgnoreCase(String username);

    List<TUser> findByStateTrue();

    List<TUser> findByStateFalse();

    List<TUser> findByIdBetween(int start, int end);

    List<TUser> findByUsernameLikeOrIdBetween(String name, int start, int end);

    // username LIKE ? AND left_money <? OR state =? AND isdel = 0
    List<TUser> findByUsernameLikeAndLeftMoneyLessThanOrState(String username, int leftMoney, byte state);

}

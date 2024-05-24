package com.jiabin.multidatasource.mybatis.annotation.practice.mapper.primary;

import com.jiabin.multidatasource.mybatis.annotation.practice.entity.People;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * PeopleMapper
 *
 **/
public interface PeopleMapper {

    @Select("SELECT * FROM people WHERE name = #{name}")
    People selectByName(@Param("name") String name);

    @Insert("INSERT INTO people(name, age) VALUE(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);
}

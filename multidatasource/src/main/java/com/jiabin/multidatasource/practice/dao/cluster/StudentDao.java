package com.jiabin.multidatasource.practice.dao.cluster;

import com.jiabin.multidatasource.practice.dao.BaseDao;
import com.jiabin.multidatasource.practice.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
* @Title: StudentDao
* @Description: 
* @Version:1.0.0
 */
@Mapper
public interface StudentDao extends BaseDao<Student> {

}

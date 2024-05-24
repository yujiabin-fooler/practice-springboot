package com.jiabin.multidatasource.practice.service.impl;

import com.jiabin.multidatasource.practice.dao.BaseDao;
import com.jiabin.multidatasource.practice.dao.cluster.StudentDao;
import com.jiabin.multidatasource.practice.pojo.Student;
import com.jiabin.multidatasource.practice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 
* Title: StudentServiceImpl
* Description: 
* 用户操作实现类 
* Version:1.0.0
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student>  implements StudentService {
	@Autowired
	private StudentDao studentDao;
	
	@Override
	protected BaseDao<Student> getMapper() {
		return this.studentDao;
	}
	
}

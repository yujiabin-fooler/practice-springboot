package com.jiabin.multidatasource.mybatis.annotation.practice.service;

import com.jiabin.multidatasource.mybatis.annotation.practice.entity.People;
import com.jiabin.multidatasource.mybatis.annotation.practice.mapper.primary.PeopleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * PeopleService
 **/
@Service
public class PeopleService {

    @Autowired
    private PeopleMapper peopleMapper;

    public People selectPeople(String name) {
        People people = peopleMapper.selectByName(name);

        return people;
    }

    @Transactional(rollbackFor = Exception.class, transactionManager = "PrimaryTransactionManager")
    public int insertPlayer(People people) {

        if (people ==null) {
            return 0;
        }

        int insert = peopleMapper.insert(people.getName(), people.getAge());
        return insert;
    }
}

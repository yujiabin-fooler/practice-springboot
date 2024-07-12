package com.jiabin.detect.faces.practice.repository;

import com.jiabin.detect.faces.practice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jiabin.yu
 * @Description:
 * @date 2021/10/29
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {

}

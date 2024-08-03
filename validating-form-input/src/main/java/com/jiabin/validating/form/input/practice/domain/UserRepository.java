package com.jiabin.validating.form.input.practice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户持久层操作接口
 *
 * @author jiabin.yu 21/07/2017.
 */
public interface UserRepository extends JpaRepository<User, Long> {

}

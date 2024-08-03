package com.jiabin.validating.form.input.practice.service;


import spring.boot.core.domain.User;

import java.util.List;

/**
 * User 业务层接口
 *
 * @author jiabin.yu 24/07/2017.
 */
public interface UserService {

    List<User> findAll();

    User insertByUser(User user);

    User update(User user);

    User delete(Long id);

    User findById(Long id);
}

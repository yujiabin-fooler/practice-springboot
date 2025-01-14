package com.jiabin.data.jpa.practice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Book 数据持久层操作接口
 *
 * @author jiabin.yu 09/10/2017.
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}

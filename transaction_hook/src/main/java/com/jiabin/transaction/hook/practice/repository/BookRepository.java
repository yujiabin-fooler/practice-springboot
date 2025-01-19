package com.jiabin.transaction.hook.practice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import com.jiabin.transaction.hook.practice.domain.Book;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

  @Lock(LockModeType.PESSIMISTIC_READ)
  @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3")})
  public Optional<Book> findById(Long id) ;
}

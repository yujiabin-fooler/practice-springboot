package com.jiabin.guava.practice.service;

import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.hash.BloomFilter;
import com.jiabin.guava.practice.domain.User;
import com.jiabin.guava.practice.repository.UserRepository;

@Service
public class UserService {

  private final Cache<Long, User> userCache ;
  private final UserRepository userRepository ;
  private final BloomFilter<Long> userBloomFilter ;

  public UserService(
      Cache<Long, User> userCache, 
      UserRepository userRepository, 
      BloomFilter<Long> userBloomFilter) {
    this.userCache = userCache ;
    this.userRepository = userRepository ;
    this.userBloomFilter = userBloomFilter ;
  }
  
  public User queryUser(Long id) throws Exception {
    if (!this.userBloomFilter.mightContain(id)) {
      return null ;
    }
    return this.userCache.get(id, () -> this.userRepository.findById(id)) ;
  }
}

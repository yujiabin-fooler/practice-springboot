package com.jiabin.caffeine.practice;

import com.jiabin.caffeine.practice.entity.User;
import com.jiabin.caffeine.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private  UserRepository userRepository;
	
	@Cacheable("users")
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}
	
	@CacheEvict(value = "users", allEntries = true)
	public void save(User user) {
		userRepository.save(user);
		
	}
	
	public User findById(  Long id) {
		 User user = userRepository.findById(id).orElse(null);
		 return user;
	}
	
	@CacheEvict(value = "users", allEntries = true)
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
}

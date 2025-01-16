package com.jiabin.guava.practice.bloomfilter;

import java.util.List;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;
import com.jiabin.guava.practice.domain.User;

public class Test1 {

  public static void main(String[] args) {
    
    final List<User> list = List.of(
        new User(1L, "Pack", 33),
        new User(1L, "JACC", 23),
        new User(1L, "XXX", 34),
        new User(1L, "OOO", 23),
        new User(1L, "ASK", 22),
        new User(2L, "Jock", 56)) ; 
    
    Funnel<User> userFunnel = new Funnel<>() {
      private static final long serialVersionUID = 1L;

      @Override
      public void funnel(User from, PrimitiveSink into) {
        into.putLong(from.getId())
        .putUnencodedChars(from.getName())
        .putInt(from.getAge()) ;
      }
    } ;
    BloomFilter<User> users = BloomFilter.create(userFunnel, 500, 0.01) ;
    for (User user : list) {
      users.put(user);
    }
    User user = new User(1L, "OOO", 23) ;
    System.err.printf("是否存在: %s%n", users.mightContain(user)) ;
    user = new User(2L, "OOO", 23) ;
    System.err.printf("是否存在: %s%n", users.mightContain(user)) ;
  }
  
}

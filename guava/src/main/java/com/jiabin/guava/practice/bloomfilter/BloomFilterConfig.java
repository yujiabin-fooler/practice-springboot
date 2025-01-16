package com.jiabin.guava.practice.bloomfilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

@Configuration
public class BloomFilterConfig {

  @Bean
  BloomFilter<Long> userBloomFilter() {
    @SuppressWarnings("serial")
    Funnel<Long> userFunnel = new Funnel<>() {
      @Override
      public void funnel(Long from, PrimitiveSink into) {
        into.putLong(from) ;
      }
    } ;
    BloomFilter<Long> bloomFilter = BloomFilter.create(userFunnel, 500, 0.01) ;
    return bloomFilter ;
  }
}

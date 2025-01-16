package com.jiabin.encrypt.filed.likequery;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jiabin.encrypt.filed.likequery.utils.SecretComponent;

import jakarta.annotation.Resource;

@SpringBootApplication
@MapperScan(basePackages = {"com.jiabin.encrypt.filed.likequery.mapper"})
public class SpringBootEncryptFiledLikequeryApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEncryptFiledLikequeryApplication.class, args);
	}

	@Resource
	private SecretComponent sc ;
	
  @Override
  public void run(String... args) throws Exception {
    String data = "13898987656" ;
    String result = this.sc.encrypt(data) ;
    System.err.println(data + ", 加密后: " + result) ;
    result = this.sc.decrypt(result) ;
    System.err.println(result) ;
  }
}

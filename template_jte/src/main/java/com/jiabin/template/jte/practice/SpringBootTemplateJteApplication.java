package com.jiabin.template.jte.practice;

import java.nio.file.Path;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootTemplateJteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTemplateJteApplication.class, args);
	}

  @Override
  public void run(String... args) throws Exception {
    System.err.println(Path.of("templates/jte/test.jte").toAbsolutePath()) ;
  }

}

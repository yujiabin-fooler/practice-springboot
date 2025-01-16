package com.jiabin.get.endpoint.practice.controller;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiabin.get.endpoint.practice.aop.TimeRecord;
import com.jiabin.get.endpoint.practice.domain.Product;

@RestController
@RequestMapping("/products")
public class ProductController {

  @PostMapping("/save")
  public ResponseEntity<Product> save(@RequestBody Product product) {
    return ResponseEntity.ok(product) ;
  }
  
  @PostMapping("/form")
  public ResponseEntity<Product> form(Product product) {
    return ResponseEntity.ok(product) ;
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    return ResponseEntity.noContent().build() ;
  }
  
  @PutMapping("/update")
  public ResponseEntity<Product> update(@RequestBody Product product) {
    return ResponseEntity.ok(product) ;
  }
  
  @GetMapping("/query")
  @TimeRecord
  public ResponseEntity<List<Product>> query(String name, Long id) {
    try {
      TimeUnit.MILLISECONDS.sleep(new Random().nextInt(2000));
    } catch (InterruptedException e) {
    }
    return ResponseEntity.ok(List.of(
          new Product()
        )) ;
  }
  
}

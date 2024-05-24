package com.jiabin.drools.practice.controller;

import com.jiabin.drools.practice.entity.Product;
import com.jiabin.drools.practice.service.DiscountService;
import com.jiabin.drools.practice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @Autowired
    private DiscountService discountService;
    
    @GetMapping("/")
    public String index(Model model) {
        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            discountService.applyDiscount(product);
        }
        model.addAttribute("products", products);
        return "index";
    }
}
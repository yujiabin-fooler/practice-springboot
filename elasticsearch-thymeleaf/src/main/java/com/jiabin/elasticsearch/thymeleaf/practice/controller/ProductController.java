package com.jiabin.elasticsearch.thymeleaf.practice.controller;

import com.jiabin.elasticsearch.thymeleaf.practice.entity.Product;
import com.jiabin.elasticsearch.thymeleaf.practice.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.list());
        return "product/list";
    }
    
    @GetMapping("/import")
    public String importProducts() {
        productService.importProductsToElasticsearch();
        return "product/import-success"; // 创建一个导入成功页面
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam String keyword, Model model) {
        // 根据关键字在Elasticsearch中搜索商品
        List<Product> products = productService.searchProducts(keyword);
        model.addAttribute("products", products);
        return "product/search-results"; // 创建一个搜索结果页面
    }
}
package com.jiabin.redis.jpa.practice.controller;

import com.jiabin.redis.jpa.practice.entity.Product;
import com.jiabin.redis.jpa.practice.service.ProductService;
import com.jiabin.redis.jpa.practice.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/seckill")
public class SeckillController {
    @Autowired
    private SeckillService seckillService;
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/list")
    public String list(Model model) {
        // 查询秒杀商品列表
        List<Product> products = productService.listSecKillProducts();
        model.addAttribute("products", products);
        return "seckill/list";
    }
    
    @PostMapping("/{productId}")
    @ResponseBody
    public ResponseEntity<String> seckill(@PathVariable Long productId) {
        // 模拟用户ID，实际中应从认证/登录信息中获取
        Long userId = 12345L;
        
        if (seckillService.seckillProduct(userId, productId)) {
            return ResponseEntity.ok("秒杀成功！");
        } else {
            return ResponseEntity.ok("秒杀失败，秒杀过此商品或商品已售罄。");
        }
    }
}
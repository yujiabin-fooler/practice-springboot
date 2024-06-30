package com.jiabin.redis.data.sharing.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{cartId}/items")
    public ResponseEntity<String> addToCart(@PathVariable String cartId,
                                            @RequestParam String productId,
                                            @RequestParam int quantity) {
        cartService.addToCart(cartId, productId, quantity);
        return ResponseEntity.ok("Item added to cart");
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Map<Object, Object>> getCart(@PathVariable String cartId) {
        Map<Object, Object> cart = cartService.getCart(cartId);
        return ResponseEntity.ok(cart);
    }
}

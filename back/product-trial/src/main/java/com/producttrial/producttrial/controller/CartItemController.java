package com.producttrial.producttrial.controller;

import com.producttrial.producttrial.model.CartItem;
import com.producttrial.producttrial.services.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartItemController
{

    @Autowired
    private ICartItemService cartService;

    @GetMapping
    public ResponseEntity<List<CartItem>> getCartItems() {
        return ResponseEntity.ok(cartService.getCartItemsForCurrentUser());
    }

    @PostMapping("/{productId}")
    public ResponseEntity<CartItem> addToCart(@PathVariable Long productId,
                                              @RequestParam(defaultValue = "1") int quantity) {
        return ResponseEntity.ok(cartService.addToCart(productId, quantity));
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<CartItem> updateQuantity(@PathVariable Long productId,@RequestParam int quantity)
    {
        return ResponseEntity.ok(cartService.updateCartItem(productId, quantity));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long productId)
    {
        cartService.removeFromCart(productId);
        return ResponseEntity.noContent().build();
    }

}

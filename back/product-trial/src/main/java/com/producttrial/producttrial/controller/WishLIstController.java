package com.producttrial.producttrial.controller;

import com.producttrial.producttrial.model.WishLIstItem;
import com.producttrial.producttrial.services.IWishListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishLIstController
{
    @Autowired
    private IWishListItemService wishlistService;

    @GetMapping
    public ResponseEntity<List<WishLIstItem>> getWishlist()
    {
        return ResponseEntity.ok(wishlistService.getWishlistForCurrentUser());
    }

    @PostMapping("/{productId}")
    public ResponseEntity<WishLIstItem> addToWishlist(@PathVariable Long productId)
    {
        return ResponseEntity.ok(wishlistService.addToWishlist(productId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeFromWishlist(@PathVariable Long productId)
    {
        wishlistService.removeFromWishlist(productId);
        return ResponseEntity.noContent().build();
    }
}

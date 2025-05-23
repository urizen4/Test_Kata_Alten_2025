package com.producttrial.producttrial.services;

import com.producttrial.producttrial.model.CartItem;
import com.producttrial.producttrial.model.User;

import java.util.List;

public interface ICartItemService {

    List<CartItem> getCartItemsForCurrentUser();

    CartItem addToCart(Long productId, int quantity);

    void removeFromCart(Long productId);

    CartItem updateCartItem(Long productId, int newQuantity);
}

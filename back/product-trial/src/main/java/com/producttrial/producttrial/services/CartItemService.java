package com.producttrial.producttrial.services;

import com.producttrial.producttrial.model.CartItem;
import com.producttrial.producttrial.model.Product;
import com.producttrial.producttrial.model.User;
import com.producttrial.producttrial.repository.CartItemRepository;
import com.producttrial.producttrial.repository.ProductRepository;
import com.producttrial.producttrial.repository.UserRepository;
import com.producttrial.producttrial.util.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService implements ICartItemService
{
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CartItem> getCartItemsForCurrentUser()
    {
        String email = AuthUtils.getCurrentUserEmail();
        return cartItemRepository.findByUserEmail(email);
    }

    @Override
    public CartItem addToCart(Long productId, int quantity) {
        String email = AuthUtils.getCurrentUserEmail();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        // Vérifie si l'article est déjà dans le panier
        return cartItemRepository.findByUserEmailAndProductId(email, productId)
                .map(item -> {
                    item.setQuantity(item.getQuantity() + quantity);
                    return cartItemRepository.save(item);
                })
                .orElseGet(() -> {
                    CartItem item = new CartItem(user, product, quantity);
                    return cartItemRepository.save(item);
                });
    }

    @Override
    public void removeFromCart(Long productId)
    {
        String email = AuthUtils.getCurrentUserEmail();
        cartItemRepository.deleteByUserEmailAndProductId(email, productId);
    }

    @Override
    public CartItem updateCartItem(Long productId, int newQuantity) {
        String email = AuthUtils.getCurrentUserEmail();
        CartItem item = cartItemRepository.findByUserEmailAndProductId(email, productId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé dans le panier"));
        item.setQuantity(newQuantity);
        return cartItemRepository.save(item);
    }
}

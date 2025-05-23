package com.producttrial.producttrial.services;


import com.producttrial.producttrial.model.Product;
import com.producttrial.producttrial.model.User;
import com.producttrial.producttrial.model.WishLIstItem;
import com.producttrial.producttrial.repository.ProductRepository;
import com.producttrial.producttrial.repository.UserRepository;
import com.producttrial.producttrial.repository.WishlistItemRepository;
import com.producttrial.producttrial.util.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListItemService implements IWishListItemService
{
    @Autowired
    private WishlistItemRepository wishlistRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<WishLIstItem> getWishlistForCurrentUser()
    {
        String email = AuthUtils.getCurrentUserEmail();
        return wishlistRepo.findByUserEmail(email);
    }

    @Override
    public WishLIstItem addToWishlist(Long productId)
    {
        String email = AuthUtils.getCurrentUserEmail();
        Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Produit introuvable"));
        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        // Vérifie s'il y a un doublon
        return wishlistRepo.findByUserEmailAndProductId(email, productId)
                .orElseGet(() -> wishlistRepo.save(new WishLIstItem(user, product)));
    }

    @Override
    public void removeFromWishlist(Long productId)
    {
        String email = AuthUtils.getCurrentUserEmail();
        wishlistRepo.deleteByUserEmailAndProductId(email, productId);
    }
}

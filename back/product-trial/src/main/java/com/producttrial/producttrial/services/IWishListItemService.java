package com.producttrial.producttrial.services;

import com.producttrial.producttrial.model.User;
import com.producttrial.producttrial.model.WishLIstItem;

import java.util.List;

public interface IWishListItemService {

    List<WishLIstItem> getWishlistForCurrentUser();

    WishLIstItem addToWishlist(Long productId);

    void removeFromWishlist(Long productId);
}

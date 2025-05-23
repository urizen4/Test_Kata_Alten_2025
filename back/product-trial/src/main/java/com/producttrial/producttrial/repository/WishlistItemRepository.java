package com.producttrial.producttrial.repository;


import com.producttrial.producttrial.model.WishLIstItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface WishlistItemRepository extends JpaRepository<WishLIstItem, Long>
{
    List<WishLIstItem> findByUserEmail(String email);
    Optional<WishLIstItem> findByUserEmailAndProductId(String email, Long productId);
    void deleteByUserEmailAndProductId(String email, Long productId);
}

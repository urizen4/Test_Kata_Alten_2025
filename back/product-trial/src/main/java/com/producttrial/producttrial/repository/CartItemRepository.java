package com.producttrial.producttrial.repository;

import com.producttrial.producttrial.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>
{
    List<CartItem> findByUserEmail(String email);
    Optional<CartItem> findByUserEmailAndProductId(String email, Long productId);
    void deleteByUserEmailAndProductId(String email, Long productId);
}

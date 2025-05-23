package com.producttrial.producttrial.services;

import com.producttrial.producttrial.model.Product;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    @Transactional
    Product createProduct(Product product);

    @Transactional
    List<Product> getAllProducts();

    @Transactional
    Optional<Product> getProductById(Long id);

    @Transactional
    Optional<Product> updateProduct(Long id, Product updatedProduct);

    @Transactional
    void deleteProduct(Long id);
}

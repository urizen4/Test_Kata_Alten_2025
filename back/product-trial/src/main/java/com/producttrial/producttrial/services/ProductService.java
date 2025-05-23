package com.producttrial.producttrial.services;


import com.producttrial.producttrial.model.Product;
import com.producttrial.producttrial.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService implements IProductService
{
    @Autowired
    private ProductRepository productRepository;



    @Override
    @Transactional
    public Product createProduct(Product product)
    {
        return productRepository.save(product);
    }


    @Override
    @Transactional
    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Product> getProductById(Long id)
    {
        return productRepository.findById(id);
    }




    @Override
    @Transactional
    public Optional<Product> updateProduct(Long id, Product updatedProduct)
    {
        return productRepository.findById(id).map(product -> {
            product.setCode(updatedProduct.getCode());
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setImage(updatedProduct.getImage());
            product.setCategory(updatedProduct.getCategory());
            product.setPrice(updatedProduct.getPrice());
            product.setQuantity(updatedProduct.getQuantity());
            product.setInternalReference(updatedProduct.getInternalReference());
            product.setShellId(updatedProduct.getShellId());
            product.setInventoryStatus(updatedProduct.getInventoryStatus());
            product.setRating(updatedProduct.getRating());
            product.setUpdatedAt(java.time.Instant.now());
            return productRepository.save(product);
        });
    }

    @Override
    @Transactional
    public void deleteProduct(Long id)
    {
        productRepository.deleteById(id);
    }


}

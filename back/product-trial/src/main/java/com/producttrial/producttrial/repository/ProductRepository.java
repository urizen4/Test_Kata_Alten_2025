package com.producttrial.producttrial.repository;

import com.producttrial.producttrial.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{

}

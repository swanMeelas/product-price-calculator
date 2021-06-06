package com.product.price.calculator.repository;

import com.product.price.calculator.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {
}

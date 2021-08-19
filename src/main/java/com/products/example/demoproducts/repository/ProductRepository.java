package com.products.example.demoproducts.repository;

import com.products.example.demoproducts.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}

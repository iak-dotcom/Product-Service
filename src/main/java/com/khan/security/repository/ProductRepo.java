package com.khan.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khan.security.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}

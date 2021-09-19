package com.example.InventoryManagement.repo;

import com.example.InventoryManagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>{

    Optional<Product> findProductById(Long id);

    void deleteProductById(Long id);
}

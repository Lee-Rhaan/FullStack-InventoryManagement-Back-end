package com.example.InventoryManagement.service;

import com.example.InventoryManagement.exceptions.ProductNotFoundException;
import com.example.InventoryManagement.model.Product;
import com.example.InventoryManagement.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private ProductRepository productRepo;

    @Autowired
    public ProductService(ProductRepository productRepo)
    {
        this.productRepo = productRepo;
    }

    public List<Product> findAllProducts()
    {
        return productRepo.findAll();
    }

    public Product addProduct(Product product)
    {
        return productRepo.save(product);
    }

    public Product updateProduct(Product product)
    {
        return productRepo.save(product);
    }

    public Product findProductById(Long id)
    {
        return productRepo.findProductById(id).orElseThrow(() -> new ProductNotFoundException("product with id: " +
                id + " not found"));
    }

    public void deleteProductById(Long id)
    {
        productRepo.deleteProductById(id);
    }

    public Integer sumOfInventory()
    {
        Integer sumOfProducts = 0;
        List<Product> products = productRepo.findAll();

        for(Product product : products)
        {
            sumOfProducts = sumOfProducts + product.getProductQuantity();
        }

        return sumOfProducts;
    }
}

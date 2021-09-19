package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.model.Product;
import com.example.InventoryManagement.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts()
    {
        List<Product> allProducts = productService.findAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product)
    {
        Product newProduct = productService.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product)
    {
        Product updateProduct = productService.updateProduct(product);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id)
    {
        Product product = productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") Long id)
    {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sum")
    public ResponseEntity<Integer> sumOfProducts()
    {
        Integer sum = productService.sumOfInventory();
        return new ResponseEntity<>(sum, HttpStatus.OK);
    }
}

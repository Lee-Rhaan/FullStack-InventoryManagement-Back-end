package com.example.InventoryManagement.integration;

import com.example.InventoryManagement.model.Product;
import com.example.InventoryManagement.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ProductService productService;

    @Test
    void getAllProducts() throws Exception {
        Product firstProduct = new Product("shoes", "500", 40, "");
        Product secondProduct = new Product("pants", "50", 60, "");

        List<Product> listProducts = new ArrayList<>();
        listProducts.add(firstProduct);
        listProducts.add(secondProduct);

        mockMvc.perform(get("http://localhost:8080/inventory/all"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void addNewProduct() throws Exception {
        Product newProduct = new Product("shoes", "500", 40, "");

        mockMvc.perform(post("http://localhost:8080/inventory/add")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(objectMapper.writeValueAsString(newProduct)))
                .andExpect(status().isCreated()).andDo(print()).andReturn();
    }

    @Test
    void updateProduct() throws Exception {
        Product existingProduct = new Product("shoes", "500", 40, "");
        existingProduct.setId(1l);

        mockMvc.perform(put("http://localhost:8080/inventory/update")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(objectMapper.writeValueAsString(existingProduct)))
                .andExpect(status().isOk());
    }

    @Test
    void getProductById() throws Exception {
        Product product = new Product("shoes", "500", 40, "");

        productService.addProduct(product);

        mockMvc.perform(get("http://localhost:8080/inventory/find/" + product.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteProductById() throws Exception {
        Product product = new Product("shoes", "500", 40, "");

        productService.addProduct(product);

        mockMvc.perform(delete("http://localhost:8080/inventory/remove/" + product.getId()))
                .andExpect(status().isOk());

    }

    @Test
    void sumOfProducts() throws Exception {
        Product firstProduct = new Product("shoes", "500", 40, "");
        Product secondProduct = new Product("pants", "50", 60, "");

        productService.addProduct(firstProduct);
        productService.addProduct(secondProduct);

        mockMvc.perform(get("http://localhost:8080/inventory/sum"))
                .andExpect(status().isOk());
    }

}

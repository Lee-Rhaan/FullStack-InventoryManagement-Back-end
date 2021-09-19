package com.example.InventoryManagement.controller;

import com.example.InventoryManagement.model.Product;
import com.example.InventoryManagement.repo.ProductRepository;
import com.example.InventoryManagement.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private static ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    ProductService productService;

    @Test
    void getAllProducts() throws Exception {
        Product firstProduct = new Product("shoes", "500", 40, "");
        Product secondProduct = new Product("pants", "50", 60, "");

        List<Product> listProducts = new ArrayList<>();
        listProducts.add(firstProduct);
        listProducts.add(secondProduct);

        Mockito.when(productService.findAllProducts()).thenReturn(listProducts);

        MvcResult mvcResult = mockMvc.perform(get("http://localhost:8080/inventory/all"))
                .andExpect(status().isOk()).andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        System.out.println(actualJsonResponse);

        String expectedJsonResponse = objectMapper.writeValueAsString(listProducts);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }

    @Test
    void addNewProduct() throws Exception {
     //   Product product = new Product("shoes", "500", 40, "");

     //   Mockito.when(productService.addProduct(ArgumentMatchers.any())).thenReturn(product);
     //   String json = objectMapper.writeValueAsString(product);
     //   mockMvc.perform(post("http://localhost:8080/inventory/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
     //           .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
     //           .andExpect(jsonPath("$.id", Matchers.equalTo(product.getId())))
     //           .andExpect(jsonPath("$.productName", Matchers.equalTo("shoes")));

        Product newProduct = new Product("shoes", "500", 40, "");
        Product savedProduct = new Product("shoes", "500", 40, "");
        savedProduct.setId(1l);

        Mockito.when(productService.addProduct(newProduct)).thenReturn(savedProduct);

        mockMvc.perform(post("http://localhost:8080/inventory/add")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(objectMapper.writeValueAsString(newProduct)))
                .andExpect(status().isCreated()).andExpect(content().string(""));

    }

    @Test
    void updateProduct() throws Exception {
        Product existingProduct = new Product("shoes", "500", 40, "");
        Product updatedProduct = new Product("shoes", "600", 60, "");
        existingProduct.setId(1l);
        updatedProduct.setId(1l);

        Mockito.when(productService.updateProduct(existingProduct)).thenReturn(updatedProduct);

        mockMvc.perform(put("http://localhost:8080/inventory/update")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(objectMapper.writeValueAsString(existingProduct)))
                .andExpect(status().isOk()).andExpect(content().string(""));
    }

    @Test
    void getProductById() throws Exception {
        Product product = new Product("shoes", "500", 40, "");
        product.setId(1l);

        Mockito.when(productService.findProductById(product.getId())).thenReturn(product);

        mockMvc.perform(get("http://localhost:8080/inventory/find/" + product.getId()))
                .andExpect(status().isOk());

        assertThat(productService.findProductById(product.getId())).isEqualTo(product);
    }

    @Test
    void deleteProductById() throws Exception {
        Long productId = 1l;

        Mockito.doNothing().when(productService).deleteProductById(productId);

        mockMvc.perform(delete("http://localhost:8080/inventory/remove/" + productId))
                .andExpect(status().isOk());

        Mockito.verify(productService, times(1)).deleteProductById(productId);
    }

    @Test
    void sumOfProducts() throws Exception {
        Product firstProduct = new Product("shoes", "500", 40, "");
        Product secondProduct = new Product("pants", "50", 60, "");

        int inventory = firstProduct.getProductQuantity() + secondProduct.getProductQuantity();

        Mockito.when(productService.sumOfInventory()).thenReturn(inventory);

        mockMvc.perform(get("http://localhost:8080/inventory/sum"))
                .andExpect(status().isOk());
    }
}
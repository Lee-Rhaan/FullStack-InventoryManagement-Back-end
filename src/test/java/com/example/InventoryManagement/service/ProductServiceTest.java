package com.example.InventoryManagement.service;

import com.example.InventoryManagement.model.Product;
import com.example.InventoryManagement.repo.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productServiceTest;

    @BeforeEach
    void setup()
    {
        productServiceTest = new ProductService(productRepository);
    }

    Product product = new Product(
            "shoes",
            "1000",
            40,
            ""
    );

    @Test
    void itShouldFindAllProducts() {
        //when
        productServiceTest.findAllProducts();

        //then
        verify(productRepository).findAll();
    }

    @Test
    void itShouldAddProduct() {
        //when
        productServiceTest.addProduct(product);

        //then
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);

        verify(productRepository).save(productArgumentCaptor.capture());

        Product capturedProduct = productArgumentCaptor.getValue();

        assertThat(capturedProduct).isEqualTo(product);
    }

    @Test
    void itShouldUpdateProduct() {
        //when
        when(productRepository.save(product)).thenReturn(product);

        //then
        assertThat(productServiceTest.updateProduct(product)).isEqualTo(product);
    }

    @Test
    void itShouldFindProductById() {
        //when
        when(productRepository.findProductById(product.getId())).thenReturn(Optional.of(product));

        //then
        assertThat(productServiceTest.findProductById(product.getId())).isEqualTo(product);
    }

    @Test
    void itShouldDeleteProductById() {
        //given
        productServiceTest.deleteProductById(product.getId());

        //when
        boolean exists = productRepository.existsById(product.getId());

        //then
        assertThat(exists).isFalse();
    }

    @Test
    void sumOfInventory() {
        /*
        This is how you test an iterator with many items

         public void testManyItemsInIterator() {
        //given
        Set<String> mockIterable = mock(Set.class);
        List<String> expectedResultsFromIterator = Arrays.asList("one", "two", "three");
        //when
        MockIterator.mockIterable(mockIterable, "one", "two", "three");
        //then
        List<String> results = new ArrayList<>();
        for (String s : mockIterable) {
            results.add(s);
        }
        assertEquals(expectedResultsFromIterator, results);
    }
         */

        //This is how you test an Iterator with one item

        //given
        Set<Integer> mockIterable = mock(Set.class);
        List<Integer> expectedResultsFromIterator = Arrays.asList(product.getProductQuantity());
        //when
        MockIterator.mockIterable(mockIterable, product.getProductQuantity());
        //then
        List<Integer> results = new ArrayList<>();
        for (Integer s : mockIterable) {
            results.add(s);
        }
        assertEquals(expectedResultsFromIterator, results);

        /*

        This is how you test an empty Iterator

        public void testEmptyIteratorResults() {
        //given
        Set<String> mockIterable = mock(Set.class);
        //when
        MockIterator.mockIterable(mockIterable);
        //then
        List<String> results = new ArrayList<>();
        for (String s : mockIterable) {
            results.add(s);
        }
        assertEquals(Collections.emptyList(), results);
    }
         */

    }
}
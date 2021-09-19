package com.example.InventoryManagement.repo;

import com.example.InventoryManagement.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepositoryTest;

    //After each test -> I want to delete everything
    //Which means for each test we'll have a clean slate
    @AfterEach
    void tearDown()
    {
        productRepositoryTest.deleteAll();
    }

    Product product = new Product(
            "shoes",
            "1000",
            40,
            ""
    );

    @Test
    void itShouldFindProductById() {
        //given
        productRepositoryTest.save(product);

        //when
        Long testId = product.getId();
        Optional<Product> expectedValue = productRepositoryTest.findProductById(testId);

        //then
        assertThat(expectedValue).isEqualTo(Optional.of(product));

    }

    @Test
    void itShouldDeleteProductById() {
        //given
        productRepositoryTest.save(product);
        productRepositoryTest.deleteProductById(product.getId());

        //when
        boolean exists = productRepositoryTest.existsById(product.getId());

        //then
        assertThat(exists).isFalse();
    }
}
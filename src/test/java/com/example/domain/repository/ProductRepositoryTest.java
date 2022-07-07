package com.example.domain.repository;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Product Repository Tests")
class ProductRepositoryTest {

    private final ProductRepository repository;

    @Autowired
    public ProductRepositoryTest(ProductRepository repository){
        this.repository = repository;
    }




}
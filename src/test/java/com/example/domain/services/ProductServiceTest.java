package com.example.domain.services;

import com.example.domain.model.Product;
import com.example.domain.repository.ProductRepository;
import com.example.domain.util.CreateProduct;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProductServiceTest {

    @InjectMocks
    ProductService service;

    @Mock
    ProductRepository repository;

    @BeforeEach
    void setUp(){
        Mockito.when(repository.save(ArgumentMatchers.any(Product.class))).thenReturn(CreateProduct.createProductWithId());
    }

    @Test
    void saveProduct_SaveProduct_WhenSuccessful(){
        Product product = service.saveProduct(CreateProduct.createProductWithId());
        Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(product.getProductId()).isNotNull();
    }

}
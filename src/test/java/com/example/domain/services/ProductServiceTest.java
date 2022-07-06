package com.example.domain.services;

import com.example.domain.model.Product;
import com.example.domain.repository.ProductRepository;
import com.example.util.CreateProduct;
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

import java.util.Optional;
import java.util.UUID;

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
        Mockito.when(repository.findById(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.of(CreateProduct.createProductWithId()));
        Mockito.doNothing().when(repository).deleteById(ArgumentMatchers.any(UUID.class));
    }

    @Test
    void saveProduct_SaveProduct_WhenSuccessful(){
        Product product = service.saveProduct(CreateProduct.createProductWithId());
        Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(product.getProductId()).isNotNull();
        Mockito.verify(repository).save(ArgumentMatchers.any(Product.class));
    }

    @Test
    void findById_ReturnProduct_WhenSuccessful(){
        Product productExpected = service.saveProduct(CreateProduct.createProductWithId());
        Product product = service.findById(productExpected.getProductId()).orElse(null);
        Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(product).isEqualTo(productExpected);
        Mockito.verify(repository).findById(product.getProductId());
    }

    @Test
    void deleteById_DeleteProduct_WhenSuccessful(){
        Assertions.assertThatCode(() -> service.deleteById(UUID.fromString("5987242e-2926-4ae2-a05a-ae294de4db8e"))).doesNotThrowAnyException();
        Mockito.verify(repository).findById(UUID.fromString("5987242e-2926-4ae2-a05a-ae294de4db8e"));
    }

}
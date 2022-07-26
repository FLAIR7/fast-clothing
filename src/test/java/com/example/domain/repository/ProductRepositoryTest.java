package com.example.domain.repository;

import com.example.domain.model.Product;
import com.example.util.CreateProduct;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@DisplayName("Product Repository Tests")
class ProductRepositoryTest {

    private final ProductRepository repository;

    @Autowired
    public ProductRepositoryTest(ProductRepository repository){
        this.repository = repository;
    }

    @Test
    void save_PersistProduct_WhenSuccessful(){
        Product product = CreateProduct.createProduct();
        Product productSaved = repository.save(product);

        Assertions.assertThat(product).isNotNull();
        Assertions.assertThat(product.getProductId()).isNotNull();
        Assertions.assertThat(product.getProductId()).isEqualTo(productSaved.getProductId());
    }

    @Test
    void findById_ReturnsProduct_WhenSuccessful(){
        Product product = CreateProduct.createProductWithId();
        Product productSaved = repository.save(product);

        Optional<Product> optProduct = repository.findById(productSaved.getProductId());

        Assertions.assertThat(optProduct).isPresent();
        Assertions.assertThat(optProduct.get().getProductId()).isNotNull();
    }

    @Test
    void save_UpdateProduct_WhenSuccessful(){
        Product product = CreateProduct.createProductWithId();
        Product productSaved = repository.save(product);
        Product productUpdated;

        productSaved.setName("IPHONE");

        productUpdated = repository.save(productSaved);
        Assertions.assertThat(productUpdated).isNotNull();
        Assertions.assertThat(productUpdated).isEqualTo(productSaved);
    }

    @Test
    void deleteById_DeleteProduct_WhenSuccessful(){
        Product product = CreateProduct.createProduct();
        Product productSaved = repository.save(product);

        repository.deleteById(productSaved.getProductId());

        Optional<Product> optProduct = repository.findById(productSaved.getProductId());
        Assertions.assertThat(optProduct).isNotPresent();
        Assertions.assertThat(optProduct).isEmpty();
    }
}
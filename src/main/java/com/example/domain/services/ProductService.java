package com.example.domain.services;

import com.example.domain.model.Product;
import com.example.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    public Product saveProduct(Product product){
        return repository.save(product);
    }

    public Product increaseInventoryAmount(Product product, Integer inventoryAmount){
        product.increaseInventoryAmount(inventoryAmount);
        return repository.save(product);
    }

}

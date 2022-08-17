package com.example.domain.services;

import com.example.domain.exceptions.NotFoundException;
import com.example.domain.model.Product;
import com.example.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable page){
        return repository.findAll(page);
    }

    @Transactional
    public Product saveProduct(Product product){
        if(null == product.getPrice()){
            product.setPrice(BigDecimal.ONE);
        }
        return repository.save(product);
    }

    @Transactional(readOnly = true)
    public Optional<Product> findById(UUID productId){
        return repository.findById(productId);
    }

    @Transactional
    public void deleteById(UUID productId){
        Optional<Product> optProduct = repository.findById(productId);

        if(optProduct.isEmpty()){
            throw new NotFoundException("Product not found");
        }

        repository.deleteById(productId);
    }


}

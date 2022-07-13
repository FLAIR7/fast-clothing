package com.example.controller;

import com.example.domain.dto.product.ProductRequest;
import com.example.domain.dto.product.ProductResponse;
import com.example.domain.mapper.ProductMapper;
import com.example.domain.model.Product;
import com.example.domain.repository.ProductRepository;
import com.example.domain.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductRepository repository;
    private final ProductService service;

    @Autowired
    public ProductController(ProductRepository repository,
                             ProductService service){
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(Pageable page){
        Page<Product> response = service.findAll(page);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> save(@Valid @RequestBody ProductRequest request){
        Product product = ProductMapper.toProduct(request);
        Product productSaved = service.saveProduct(product);
        ProductResponse response = ProductMapper.toProductResponse(productSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

package com.example.controller;

import com.example.domain.dto.product.ProductRequest;
import com.example.domain.dto.product.ProductResponse;
import com.example.domain.mapper.ProductMapper;
import com.example.domain.model.Product;
import com.example.domain.repository.ProductRepository;
import com.example.domain.services.ProductService;
import com.example.exceptions.NotFoundException;
import org.apache.coyote.Response;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

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
    public ResponseEntity<Page<Product>> findAll(@ParameterObject Pageable page){
        Page<Product> response = service.findAll(page);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable UUID id){
        Optional<Product> product = service.findById(id);

        if(product.isEmpty()) {
            throw new NotFoundException("Product Not found");
        }
        ProductResponse response = ProductMapper.toProductResponse(product.get());
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
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

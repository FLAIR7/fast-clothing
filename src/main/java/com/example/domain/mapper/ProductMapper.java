package com.example.domain.mapper;

import com.example.domain.dto.product.ProductRequest;
import com.example.domain.dto.product.ProductResponse;
import com.example.domain.model.Product;
import org.modelmapper.ModelMapper;

public class ProductMapper {

    public static Product toProduct(ProductRequest request) {
        return new ModelMapper().map(request, Product.class);
    }

    public static ProductResponse toProductResponse(Product product) {
        return new ModelMapper().map(product, ProductResponse.class);
    }
}

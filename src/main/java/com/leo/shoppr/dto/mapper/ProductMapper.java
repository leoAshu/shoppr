package com.leo.shoppr.dto.mapper;

import com.leo.shoppr.dto.response.ProductResponse;
import com.leo.shoppr.entity.Product;

import java.math.BigDecimal;

public class ProductMapper {

//    public Product toEntity(CreatePr)

    public ProductResponse toDTO(Product product) {
        ProductResponse response = new ProductResponse();

        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());

        return response;
    }
}

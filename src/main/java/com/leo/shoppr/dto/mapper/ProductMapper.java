package com.leo.shoppr.dto.mapper;

import com.leo.shoppr.dto.request.CreateProductRequest;
import com.leo.shoppr.dto.response.ProductResponse;
import com.leo.shoppr.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(CreateProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .stock(productRequest.getStock())
                .build();
    }

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

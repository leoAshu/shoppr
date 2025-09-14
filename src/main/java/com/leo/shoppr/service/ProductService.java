package com.leo.shoppr.service;

import com.leo.shoppr.dto.mapper.ProductMapper;
import com.leo.shoppr.dto.request.CreateProductRequest;
import com.leo.shoppr.dto.response.ProductResponse;
import com.leo.shoppr.exception.ProductNotFoundException;
import com.leo.shoppr.entity.Product;
import com.leo.shoppr.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public List<ProductResponse> getAllProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(p -> productMapper.toDTO(p))
                .toList();
    }

    public ProductResponse getProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) throw new ProductNotFoundException(id);

        return productMapper.toDTO(product.get());
    }

    public ProductResponse getProductByName(String name) {
        Optional<Product> product = productRepository.findByName(name);
        if (product.isEmpty()) throw new ProductNotFoundException(name);

        return productMapper.toDTO(product.get());
    }

    public ProductResponse createProduct(CreateProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);

        return productMapper.toDTO(productRepository.save(product));
    }

    public ProductResponse updateProduct(String id, CreateProductRequest productRequest) {
        Product existingProduct = productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setStock(productRequest.getStock());

        return productMapper.toDTO(productRepository.save(existingProduct));
    }

    public void deleteProduct(String id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(existingProduct);
    }
}

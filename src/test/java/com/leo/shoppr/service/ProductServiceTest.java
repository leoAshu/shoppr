package com.leo.shoppr.service;

import com.leo.shoppr.exception.ProductNotFoundException;
import com.leo.shoppr.entity.Product;
import com.leo.shoppr.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        product1 = Product.builder()
                .name("Laptop")
                .description("14-inch ultrabook")
                .price(BigDecimal.valueOf(1200.00))
                .stock(10)
                .build();

        product2 = Product.builder()
                .name("Phone")
                .description("AMOLED screen")
                .price(BigDecimal.valueOf(800.00))
                .stock(20)
                .build();
    }

    @Test
    @DisplayName("Get All Products")
    public void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
        assertEquals("Laptop", products.get(0).getName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Get Existing Product by Id")
    public void testGetProductById_Found() {
        when(productRepository.findByName("Laptop")).thenReturn(Optional.of(product1));

        Product product = productService.getProductByName("Laptop");

        assertNotNull(product);
        assertEquals("Laptop", product.getName());
        verify(productRepository, times(1)).findByName("Laptop");
    }

    @Test
    @DisplayName("Get Non-Existing Product by Id")
    public void testGetProductById_NotFound() {
        when(productRepository.findByName("PlayStation")).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductByName("PlayStation"));
        verify(productRepository, times(1)).findByName("PlayStation");
    }

    // Additional Testcases Pending
}
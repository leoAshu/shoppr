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

        product1 = new Product("p1", "Laptop", "14-inch ultrabook", 1200.0, 10);
        product2 = new Product("p2", "Phone", "AMOLED screen", 800.0, 20);
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
        when(productRepository.findById("p1")).thenReturn(Optional.of(product1));

        Product product = productService.getProductById("p1");

        assertNotNull(product);
        assertEquals("p1", product.getId());
        verify(productRepository, times(1)).findById("p1");
    }

    @Test
    @DisplayName("Get Non-Existing Product by Id")
    public void testGetProductById_NotFound() {
        when(productRepository.findById("p99")).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById("p99"));
        verify(productRepository, times(1)).findById("p99");
    }

    // Additional Testcases Pending
}
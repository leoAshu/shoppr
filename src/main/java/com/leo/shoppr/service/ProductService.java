package com.leo.shoppr.service;

import com.leo.shoppr.exception.ProductNotFoundException;
import com.leo.shoppr.model.Product;
import com.leo.shoppr.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

//    private List<Product> products = new ArrayList<>(Arrays.asList(
//            new Product("p1", "Laptop", "14-inch ultrabook with 16GB RAM", 999.99, 12),
//            new Product("p2", "Smartphone", "6.5-inch AMOLED display, 128GB storage", 699.49, 30),
//            new Product("p3", "Headphones", "Noise-cancelling over-ear headphones", 149.89, 50),
//            new Product("p4", "Monitor", "27-inch 4K UHD monitor", 329.00, 15),
//            new Product("p5", "Keyboard", "Mechanical keyboard with RGB lighting", 89.99, 40)
//    ));


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product createProduct(Product product) {
        String newId = "p" + (productRepository.count() + 1);
        product.setId(newId);

        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        updatedProduct.setId(existingProduct.getId());

        return productRepository.save(updatedProduct);
    }

    public void deleteProduct(String id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(existingProduct);
    }
}

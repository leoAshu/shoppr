package com.leo.shoppr.service;

import com.leo.shoppr.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>(Arrays.asList(
            new Product("p1", "Laptop", "14-inch ultrabook with 16GB RAM", 999.99, 12),
            new Product("p2", "Smartphone", "6.5-inch AMOLED display, 128GB storage", 699.49, 30),
            new Product("p3", "Headphones", "Noise-cancelling over-ear headphones", 149.89, 50),
            new Product("p4", "Monitor", "27-inch 4K UHD monitor", 329.00, 15),
            new Product("p5", "Keyboard", "Mechanical keyboard with RGB lighting", 89.99, 40)
    ));


    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(String id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Product createProduct(Product product) {
        String newId = "p" + (products.size() + 1);
        product.setId(newId);
        products.add(product);
        return product;
    }

    public Product updateProduct(String id, Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                updatedProduct.setId(id); // ensure ID stays consistent
                products.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null;
    }

    public void deleteProduct(String id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}

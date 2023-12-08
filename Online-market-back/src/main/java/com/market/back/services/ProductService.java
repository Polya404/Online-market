package com.market.back.services;

import com.market.back.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    Long getQuantityOfOneProduct(String id);
    void addIdProductInProductStock();
    void updateQuantityOfProduct(String id, Long quantity);
    Product getProductById(String id);
    Product createProduct(Product product);
    void deleteProduct(String id);
}

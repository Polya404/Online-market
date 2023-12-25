package com.market.back.services;

import com.market.back.models.categories.Categories;
import com.market.back.models.categories.Products;

import java.util.List;
import java.util.Set;

public interface ProductService {
    List<Products> getAllProduct();
    Set<String> getCategories();
    Long getQuantityOfOneProduct(String id);
    void addIdProductInProductStock();
    void updateQuantityOfProduct(String id, Long quantity);
    Products getProductById(String id);
    Products createProduct(Products product, Categories category);
    void deleteProduct(String id);
}

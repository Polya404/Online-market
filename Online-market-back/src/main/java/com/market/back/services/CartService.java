package com.market.back.services;

import com.market.back.dto.CartItemDTO;
import com.market.back.models.categories.Products;

public interface CartService {
    CartItemDTO addProduct(Products product);
    void deleteProduct(Products product);
}

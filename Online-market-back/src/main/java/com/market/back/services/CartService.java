package com.market.back.services;

import com.market.back.dto.ProductDTO;

public interface CartService {
    void addProduct(ProductDTO product);
    void deleteProduct(ProductDTO productDTO);
}

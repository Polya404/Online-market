package com.market.back.services.impl;

import com.market.back.dto.CartItemDTO;
import com.market.back.models.CartItem;
import com.market.back.models.categories.Products;
import com.market.back.services.CartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private static CartItem cartItem;
    private static List<Products> products;

    @Override
    public CartItemDTO addProduct(Products product) {
        products.add(product);
        cartItem.setProducts(products);
        return new CartItemDTO().fromEntity(cartItem);
    }

    @Override
    public void deleteProduct(Products product) {
        products.remove(product);
    }
}

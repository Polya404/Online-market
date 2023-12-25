package com.market.back.controllers;

import com.market.back.dto.CartItemDTO;
import com.market.back.models.categories.Products;
import com.market.back.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping("/add")
    public CartItemDTO addProductToCart(Products product){
        return cartService.addProduct(product);
    }

    @PostMapping("/delete")
    public void deleteProductFromCart(Products products){
        cartService.deleteProduct(products);
    }
}

package com.market.back.services.impl;

import com.market.back.dto.CartItem;
import com.market.back.dto.ProductDTO;
import com.market.back.services.CartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private CartItem cartItem;
    private List<ProductDTO> products;

    @Override
    public void addProduct(ProductDTO product) {
        products.add(product);
        cartItem.setProducts(products);
    }

    @Override
    public void deleteProduct(ProductDTO productDTO) {
        products.remove(productDTO);
    }
}

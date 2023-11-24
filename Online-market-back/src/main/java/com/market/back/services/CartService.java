package com.market.back.services;

import com.market.back.dto.CartItem;
import com.market.back.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private CartItem cartItem;
    private List<ProductDTO> products;

    public void addProduct(ProductDTO product) {
        products.add(product);
        cartItem.setProducts(products);
    }

    public void deleteProduct(ProductDTO productDTO) {
        products.remove(productDTO);
    }
}

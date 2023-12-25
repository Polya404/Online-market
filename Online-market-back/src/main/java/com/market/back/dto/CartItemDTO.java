package com.market.back.dto;

import com.market.back.models.CartItem;
import com.market.back.models.categories.Products;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private List<Products> products;
    private Long quantity;

    public CartItemDTO fromEntity(CartItem cartItem){
        this.products = cartItem.getProducts();
        this.quantity = cartItem.getQuantity();
        return this;
    }
}

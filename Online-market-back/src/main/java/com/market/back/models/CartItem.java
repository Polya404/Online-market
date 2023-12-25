package com.market.back.models;

import com.market.back.models.categories.Products;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private List<Products> products;
    private Long quantity;
}

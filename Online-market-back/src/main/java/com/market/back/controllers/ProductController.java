package com.market.back.controllers;

import com.market.back.models.Product;
import com.market.back.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(Product product){
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @PostMapping("/{id}")
    public void deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
    }
}

package com.market.back.services;

import com.market.back.models.Product;
import com.market.back.models.ProductStock;
import com.market.back.repositories.ProductRepositoryMongo;
import com.market.back.repositories.ProductRepositoryPostgres;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepositoryMongo productRepositoryMongo;
    private final ProductRepositoryPostgres productRepositoryPostgres;

    public List<Product> getAllProduct() {
        return productRepositoryMongo.findAll();
    }

    public Long getQuantityOfOneProduct(String id) {
        return productRepositoryPostgres.findById(id).orElseThrow().getQuantity();
    }

    public void addIdProductInProductStock() {
        List<ProductStock> list = getAllProduct().stream()
                .map(product -> {
                    ProductStock productStock = new ProductStock();
                    productStock.setProductMongoId(product.getId());
                    return productStock;
                })
                .toList();

        productRepositoryPostgres.saveAll(list);
    }

    public void updateQuantityOfProduct(String id, Long quantity) {
        productRepositoryPostgres.findById(id).ifPresent(productStock -> {
            productStock.setQuantity(quantity);
            productRepositoryPostgres.save(productStock);
        });
    }
}

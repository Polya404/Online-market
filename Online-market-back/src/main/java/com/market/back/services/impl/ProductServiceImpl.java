package com.market.back.services.impl;

import com.market.back.models.Product;
import com.market.back.models.ProductStock;
import com.market.back.repositories.ProductRepositoryMongo;
import com.market.back.repositories.ProductRepositoryPostgres;
import com.market.back.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepositoryMongo productRepositoryMongo;
    private final ProductRepositoryPostgres productRepositoryPostgres;

    @Override
    public List<Product> getAllProduct() {
        return productRepositoryMongo.findAll();
    }

    @Override
    public Long getQuantityOfOneProduct(String id) {
        return productRepositoryPostgres.findById(id).orElseThrow().getQuantity();
    }

    @Override
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

    @Override
    public void updateQuantityOfProduct(String id, Long quantity) {
        productRepositoryPostgres.findById(id).ifPresent(productStock -> {
            productStock.setQuantity(quantity);
            productRepositoryPostgres.save(productStock);
        });
    }

    @Override
    public Product getProductById(String id) {
        return productRepositoryMongo.findById(id).orElseThrow();
    }

    @Override
    public Product createProduct(Product product){
        return productRepositoryMongo.insert(product);
    }

    @Override
    public void deleteProduct(String id){
        productRepositoryMongo.deleteById(id);
    }
}

package com.market.back.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.back.models.ProductStock;
import com.market.back.models.categories.*;
import com.market.back.repositories.*;
import com.market.back.services.ProductService;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final MongoTemplate mongoTemplate;
    private final ObjectMapper objectMapper;
    private final BookRepository bookRepository;
    private final ClothesRepository clothesRepository;
    private final HouseholdRepository householdRepository;
    private final ToyRepository toyRepository;
    private final MongoDatabase database;
    private final ProductRepositoryPostgres productRepositoryPostgres;

    @Override
    public List<Products> getAllProduct() {
        List<Products> allProducts = new ArrayList<>();

        List<Book> books = bookRepository.findAll();
        List<Toy> toys = toyRepository.findAll();
        List<Household> households = householdRepository.findAll();
        List<Clothes> clothesList = clothesRepository.findAll();

        allProducts.addAll(books);
        allProducts.addAll(toys);
        allProducts.addAll(households);
        allProducts.addAll(clothesList);
        return allProducts;
    }

    @Override
    public Set<String> getCategories() {
        return mongoTemplate.getCollectionNames();
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
    @SneakyThrows
    public Products getProductById(String id) {
        for (String collectionName : database.listCollectionNames()) {
            MongoCollection<Document> collection = database.getCollection(collectionName);
            Document document = collection.find(new Document("_id", new ObjectId(id))).first();
            if (document != null) {
                return (Products) objectMapper.convertValue(document.toJson(), Class.forName("com.market.back.models.categories." + collectionName));
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Products createProduct(Products product, Categories category) {
        switch (category){
            case BOOK -> bookRepository.insert((Book) product);
            case TOY -> toyRepository.insert((Toy) product);
            case HOUSEHOLD -> householdRepository.insert((Household) product);
            case CLOTHES -> clothesRepository.insert((Clothes) product);
            default -> throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void deleteProduct(String id) {
        for (String collectionName : database.listCollectionNames()) {
            MongoCollection<Document> collection = database.getCollection(collectionName);
            collection.deleteOne(new Document("_id", new ObjectId(id)));
        }
    }
}

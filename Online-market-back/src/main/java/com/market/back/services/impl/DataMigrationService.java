package com.market.back.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.back.models.Product;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class DataMigrationService {

    private final MongoTemplate mongoTemplate;
    private final ObjectMapper objectMapper;
    @Value("${config.property.path}")
    private String path;

    public void migrateData() {
        insertDataFromFile();
        log.info("Successfully validated mongoDB migration");
    }

    @SneakyThrows
    public void insertDataFromFile() {
        String jsonData = Files.readString(Paths.get(path));

        List<Map<String, String>> data = objectMapper.readValue(jsonData, new TypeReference<>() {
        });

        List<Product> products = data.stream()
                .map(map -> Product.builder().fields(map).build())
                .filter(this::existsInDatabase)
                .collect(Collectors.toList());

        mongoTemplate.insert(products, "products");
    }

    private boolean existsInDatabase(Product product) {
        Query query = new Query(Criteria.where("fields.id").is(product.getFields().get("id")));
        return !mongoTemplate.exists(query, Product.class, "products");
    }
}


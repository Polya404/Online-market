package com.market.back.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.market.back.models.categories.Products;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        Gson gson = new Gson();
        Map[] maps = gson.fromJson(jsonData, Map[].class);

        Map<String, List<Map<String, String>>> map = maps[0];
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            List<Map<String, String>> list = map.get(key);
            for (Map<String, String> model : list){
                Object o = objectMapper.convertValue(model, Class.forName("com.market.back.models.categories." + key));
                if(!mongoTemplate.collectionExists(key) || !existsInDatabase((Products) o, key)){
                    mongoTemplate.insert(o, key);
                }
            }
        }
    }

    @SneakyThrows
    private boolean existsInDatabase(Products product, String collectionName) {
        Field id = product.getClass().getDeclaredField("id");
        id.setAccessible(true);
        String value = (String) id.get(product);
        Query query = new Query(Criteria.where("_id").is(value));
        return mongoTemplate.exists(query, collectionName);
    }
}


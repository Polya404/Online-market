package com.market.back.repositories;

import com.market.back.models.categories.Clothes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories
public interface ClothesRepository extends MongoRepository<Clothes, String> {
}

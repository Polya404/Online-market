package com.market.back.repositories;

import com.market.back.models.categories.Toy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories
public interface ToyRepository extends MongoRepository<Toy, String> {
}

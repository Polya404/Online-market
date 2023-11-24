package com.market.back.repositories;

import com.market.back.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;


@Repository
@EnableMongoRepositories
public interface ProductRepositoryMongo extends MongoRepository<Product, Long> {

}

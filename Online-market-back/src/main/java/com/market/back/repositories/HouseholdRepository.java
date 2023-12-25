package com.market.back.repositories;

import com.market.back.models.categories.Household;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories
public interface HouseholdRepository extends MongoRepository<Household, String> {
}

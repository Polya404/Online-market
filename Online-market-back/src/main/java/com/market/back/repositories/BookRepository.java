package com.market.back.repositories;

import com.market.back.models.categories.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories
public interface BookRepository extends MongoRepository<Book, String> {
}

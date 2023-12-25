package com.market.back.repositories;

import com.market.back.models.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryPostgres extends JpaRepository<ProductStock, String> {
}

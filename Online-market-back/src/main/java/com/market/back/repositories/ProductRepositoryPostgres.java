package com.market.back.repositories;

import com.market.back.models.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoryPostgres extends JpaRepository<ProductStock, String> {
}

package com.mcca.products_services.repositories;

import com.mcca.products_services.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends  JpaRepository<Product, Long>{
}

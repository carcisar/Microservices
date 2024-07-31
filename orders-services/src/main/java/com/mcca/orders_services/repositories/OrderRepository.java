package com.mcca.orders_services.repositories;

import com.mcca.orders_services.model.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}

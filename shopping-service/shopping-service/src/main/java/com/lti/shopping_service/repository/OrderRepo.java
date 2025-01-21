package com.lti.shopping_service.repository;

import com.lti.shopping_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
}

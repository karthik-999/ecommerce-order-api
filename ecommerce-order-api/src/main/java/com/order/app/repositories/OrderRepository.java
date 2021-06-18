package com.order.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.app.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {



}

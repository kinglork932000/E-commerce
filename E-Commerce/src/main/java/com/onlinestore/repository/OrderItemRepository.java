package com.onlinestore.repository;

import org.springframework.data.repository.CrudRepository;

import com.onlinestore.domain.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem,Long> {
}

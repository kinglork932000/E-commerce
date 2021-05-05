package com.onlinestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.onlinestore.domain.Order;
import com.onlinestore.domain.User;

public interface OrderRepository extends CrudRepository<Order,Long> {
	List<Order> findByUser(User user);
	@Query(value = "SELECT * FROM orders p WHERE p.order_id = ?1", nativeQuery = true)
	Order findByOrder_id (int order_id);
}
package com.onlinestore.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.onlinestore.domain.Order;
import com.onlinestore.domain.User;

public interface OrderService {
	Order save(Order order);
	List<Order> findAll();
	List<Order> findByUser(User user);
	Order findByOrder_id(int order_id);
	
	Page<Order> findOrderPaginated(Pageable pageable);
	Page<Order> findOrderByUserPaginated(String username ,Pageable pageable);
}

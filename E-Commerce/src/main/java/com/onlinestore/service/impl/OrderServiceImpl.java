package com.onlinestore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlinestore.domain.Order;
import com.onlinestore.domain.User;
import com.onlinestore.repository.OrderPaginationRepository;
import com.onlinestore.repository.OrderRepository;
import com.onlinestore.repository.PaginationRepository;
import com.onlinestore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderPaginationRepository orderPaginationRepository;
	
	public Order save(Order order) {
		return orderRepository.save(order);
	}
	
	public List<Order> findAll(){
		return (List<Order>) orderRepository.findAll();
	}
	public List<Order> findByUser(User user){
		return orderRepository.findByUser(user);
	}
	public Order findByOrder_id(int order_id) {
		return orderRepository.findByOrder_id(order_id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Order> findOrderPaginated(Pageable pageable) {
		return orderPaginationRepository.findOrderPaginated(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Order> findOrderByUserPaginated(String username ,Pageable pageable) {
		return orderPaginationRepository.findOrderByUserPaginated(username, pageable);
	}
}

package com.onlinestore.service;

import java.util.List;

import com.onlinestore.domain.CartItem;
import com.onlinestore.domain.Order;
import com.onlinestore.domain.OrderItem;

public interface OrderItemService {
	List<OrderItem> saveAll(List<OrderItem> orderitems);
	List<OrderItem> saveFromCart(List<CartItem> cartitems, Order order);
}

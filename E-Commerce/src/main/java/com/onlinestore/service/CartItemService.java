package com.onlinestore.service;

import java.util.List;

import com.onlinestore.domain.CartItem;
import com.onlinestore.domain.Product;
import com.onlinestore.domain.User;

public interface CartItemService {
	List<CartItem> findByUser(User user);
	CartItem findByID(int cartitem_id);
	CartItem updateCartItem(CartItem cartItem);
	void updateQuantity(CartItem cartItem, int quantity);
	CartItem addProductToCartItem(Product product, User user, int quantity);
	void deleteItem(CartItem cartItem);
	boolean checkQuantity(List<CartItem> cartItems);
}
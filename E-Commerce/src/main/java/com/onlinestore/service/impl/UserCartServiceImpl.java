package com.onlinestore.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinestore.domain.CartItem;
import com.onlinestore.domain.User;
import com.onlinestore.repository.UserCartRepository;
import com.onlinestore.service.CartItemService;
import com.onlinestore.service.UserCartService;

@Service
public class UserCartServiceImpl implements UserCartService {

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private UserCartRepository userCartRepository;

	public User updateUserCart(User user) {

		BigDecimal cartTotal = new BigDecimal(0);

		List<CartItem> cartItemList = cartItemService.findByUser(user);

		for(CartItem cartItem : cartItemList) {
			if (cartItem.getProduct().getQuantity() > 0) {
				cartItemService.updateCartItem(cartItem);
				cartTotal = cartTotal.add(cartItem.getSubtotal());
			}
		}
		user.setGrandTotal(cartTotal);
		userCartRepository.save(user);

		return user;

	}
}
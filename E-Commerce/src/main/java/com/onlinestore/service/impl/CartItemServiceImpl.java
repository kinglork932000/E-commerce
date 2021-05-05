package com.onlinestore.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinestore.domain.CartItem;
import com.onlinestore.domain.Product;
import com.onlinestore.domain.User;
import com.onlinestore.repository.CartItemRepository;
import com.onlinestore.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;

	public List<CartItem> findByUser(User user){
		return cartItemRepository.findByUser(user);
	}
	
	public CartItem findByID(int cartitem_id) {
		return cartItemRepository.findByID(cartitem_id);
	}
	
	public void updateQuantity(CartItem cartItem, int quantity) {
		if(quantity == 0) {
			System.out.println("Hello there");
			cartItemRepository.deleteByID(cartItem.getCartitem_id());
			return;
		}
		cartItem.setQuantity(quantity);
		cartItem.setSubtotal(new BigDecimal(cartItem.getProduct().getPrice()).multiply(new BigDecimal(quantity)));
		cartItem = cartItemRepository.save(cartItem);
	}

	public CartItem updateCartItem(CartItem cartItem) {
		BigDecimal bigDecimal= new BigDecimal(cartItem.getProduct().getPrice()).multiply(new BigDecimal(cartItem.getQuantity()));
		bigDecimal = bigDecimal.setScale(2, RoundingMode.CEILING);
		cartItem.setSubtotal(bigDecimal);
		cartItemRepository.save(cartItem);
		return cartItem;
	}
	
	public CartItem addProductToCartItem(Product product, User user, int quantity) {
		List<CartItem> cartItemList = findByUser(user);
		
		for(CartItem cartItem : cartItemList) {
			if (product.getProduct_id() == cartItem.getProduct().getProduct_id()) {
				if(cartItem.getQuantity() == 0) {
					cartItem.setQuantity(quantity);
					cartItem.setSubtotal(new BigDecimal(product.getPrice()).multiply(new BigDecimal(quantity)));
					cartItem = cartItemRepository.save(cartItem);
				}
				return cartItem;
			}
		}
		
		CartItem cartItem = new CartItem();
		cartItem.setUser(user);
		cartItem.setProduct(product);
		
		cartItem.setQuantity(quantity);
		cartItem.setSubtotal(new BigDecimal(product.getPrice()).multiply(new BigDecimal(quantity)));
		cartItem = cartItemRepository.save(cartItem);
		return cartItem;

	}
	public void deleteItem(CartItem cartItem) {
		cartItemRepository.deleteByID(cartItem.getCartitem_id());
	}
	
	public boolean checkQuantity(List<CartItem> cartItems) {
		for(CartItem cartitem : cartItems) {
			if(cartitem.getQuantity() > cartitem.getProduct().getQuantity()) {
				return false;
			}
		}
		return true;
	}
}
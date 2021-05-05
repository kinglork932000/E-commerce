package com.onlinestore.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.onlinestore.domain.CartItem;
import com.onlinestore.domain.User;
import com.onlinestore.service.CartItemService;
import com.onlinestore.service.UserCartService;
import com.onlinestore.service.UserService;

@ControllerAdvice
public class GlobalController {
	@Autowired
	UserService userService;
	
	@Autowired
	UserCartService userCartService;
	
	@Autowired
	CartItemService cartItemService;
	
	@ModelAttribute
	public void headerCart(Model model, Principal principle) {
		if(SecurityContextHolder.getContext().getAuthentication().getName()=="anonymousUser") {
			return;
		}
		User user = userService.findByUsername(principle.getName());
		List<CartItem> cartItems = cartItemService.findByUser(user);
		userCartService.updateUserCart(user);
		if(cartItems.isEmpty()) {
			model.addAttribute("isCartHeaderEmpty",true);
		}
		model.addAttribute("cartHeaderNumber",cartItems.size());
		model.addAttribute("cartHeaderItems", cartItems);
		model.addAttribute("cartHeaderTotal", user.getGrandTotal());
	}
}

package com.onlinestore.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.onlinestore.domain.CartItem;
import com.onlinestore.domain.Order;
import com.onlinestore.domain.Product;
import com.onlinestore.domain.User;
import com.onlinestore.service.CartItemService;
import com.onlinestore.service.OrderItemService;
import com.onlinestore.service.OrderService;
import com.onlinestore.service.ProductService;
import com.onlinestore.service.UserCartService;
import com.onlinestore.service.UserService;

@Controller
public class CheckoutController {
	@Autowired
	private UserService userService;

	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private UserCartService userCartService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@RequestMapping("/checkout")
	public String checkout(Model model, Principal principal, RedirectAttributes redirectAttribute) {
		User user = userService.findByUsername(principal.getName());
		List<CartItem> cartItemList = cartItemService.findByUser(user);
		if(!cartItemService.checkQuantity(cartItemList)) {
			redirectAttribute.addFlashAttribute("notEnoughStock",true);
			return "redirect:/shoppingCart/cart";
		}
		userCartService.updateUserCart(user);
		model.addAttribute("cartItems", cartItemList);
		model.addAttribute("user", user);
		return "checkout";
	}
	
	@RequestMapping("/process")
	public String process(@ModelAttribute("order") Order order, Principal principal, RedirectAttributes redirectAttribute) {
		User user = userService.findByUsername(principal.getName());
		List<CartItem> cartItemList = cartItemService.findByUser(user);
		if(!cartItemService.checkQuantity(cartItemList)) {
			redirectAttribute.addFlashAttribute("notEnoughStock",true);
			return "redirect:/shoppingCart/cart";
		}
		order.setStatus(false);
		order.setUser(user);
		order.setAmount(user.getGrandTotal().doubleValue()*1.05);
		order = orderService.save(order);
		orderItemService.saveFromCart(cartItemList, order);
		//return "redirect:/thankyou";
		return "redirect:/";
	}
	
	@RequestMapping("/thankyou")
	public String thankyou() {
		return "thankyou";
	}
}

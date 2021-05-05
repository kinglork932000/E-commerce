package com.onlinestore.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.onlinestore.domain.CartItem;
import com.onlinestore.domain.Product;
import com.onlinestore.domain.User;
import com.onlinestore.service.CartItemService;
import com.onlinestore.service.ProductService;
import com.onlinestore.service.UserCartService;
import com.onlinestore.service.UserService;

@Controller
@RequestMapping("/shoppingCart") 
public class ShoppingCartController {

	@Autowired
	private UserService userService;

	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private UserCartService userCartService;

	@RequestMapping("/cart")
	public String shoppingCart(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());

		List<CartItem> cartItemList = cartItemService.findByUser(user);
		userCartService.updateUserCart(user);
		if(cartItemList.isEmpty()) {
			model.addAttribute("emptyCart", true);
		}
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("user", user);

		return "cart";
	}
	
	@RequestMapping("/addItem")
	public String addItem(
			@ModelAttribute("product") int product_id,
			@ModelAttribute("quantity") int quantity,
			Model model, Principal principal, HttpServletRequest request
			) {
		//int product_id = Integer.parseInt(stringid);
		User user = userService.findByUsername(principal.getName());
		Product product = productService.findByID(product_id);
		
		if (quantity > product.getQuantity()) {
			model.addAttribute("notEnoughStock", true);
			return "redirect:"+request.getHeader("Referer");
		}
		
		cartItemService.addProductToCartItem(product, user, quantity);
		model.addAttribute("addProductSuccess", true);
		return "redirect:"+request.getHeader("Referer");
	}
	
	@RequestMapping("/updateCartItem")
	public String updateCart(
			@ModelAttribute("cartitem_id") int cartitem_id,
			@ModelAttribute("quantity") int quantity,
			RedirectAttributes redirectAttribute,
			Model model, Principal principal) {
		CartItem cartItem = cartItemService.findByID(cartitem_id);
		if (quantity > cartItem.getProduct().getQuantity()) {
			redirectAttribute.addFlashAttribute("notEnoughStock", true);
			return "redirect:/shoppingCart/cart";
		}
		cartItemService.updateQuantity(cartItem, quantity);
		return "redirect:/shoppingCart/cart";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int cartitem_id, HttpServletRequest request) {
		CartItem cartItem = cartItemService.findByID(cartitem_id);
		cartItemService.deleteItem(cartItem);
		return "redirect:"+request.getHeader("Referer");
	}
}
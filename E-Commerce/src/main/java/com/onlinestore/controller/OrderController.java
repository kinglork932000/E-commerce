package com.onlinestore.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlinestore.domain.Order;
import com.onlinestore.domain.Product;
import com.onlinestore.domain.User;
import com.onlinestore.service.OrderService;
import com.onlinestore.service.UserService;

@Controller
public class OrderController {
	@Autowired
	UserService userService;

	@Autowired
	OrderService orderService;

	@RequestMapping("/orderhistory")
	public String orderHistory(Model model, Principal principal,
			@RequestParam(name = "page", defaultValue = "0") int page, @ModelAttribute("sorttype") String sorttype) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String optionActiveSort;
		Pageable pageRequest;
		if (sorttype.equalsIgnoreCase("2")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("order_id").descending());
			optionActiveSort = "t2";
		} else if (sorttype.equalsIgnoreCase("3")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("order_id").ascending());
			optionActiveSort = "t3";
		} else if (sorttype.equalsIgnoreCase("4")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("status").descending());
			optionActiveSort = "t4";
		} else if (sorttype.equalsIgnoreCase("5")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("status").ascending());
			optionActiveSort = "t5";
		} else if (sorttype.equalsIgnoreCase("6")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("amount").descending());
			optionActiveSort = "t6";
		} else if (sorttype.equalsIgnoreCase("7")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("amount").ascending());
			optionActiveSort = "t7";
		} else {
			pageRequest = PageRequest.of(page, 12);
			optionActiveSort = "t1";
		}
		model.addAttribute(optionActiveSort, true);
		Page<Order> orders;
		boolean hasAdminRole = authentication.getAuthorities().stream()
				.anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		if (hasAdminRole) {
			orders = orderService.findOrderPaginated(pageRequest);
		} else {
			User user = userService.findByUsername(principal.getName());
			String username = user.getUsername();
			orders = orderService.findOrderByUserPaginated(username, pageRequest);
		}

		int totalPage = orders.getTotalPages();
		List<Integer> pages = new ArrayList<Integer>();
		if (totalPage == 0) {
			pages.add(0);
		} else {
			for (int i = 0; i < totalPage; i++) {
				pages.add(i);
			}
		}
		model.addAttribute("pages", pages);
		model.addAttribute("totalpage", totalPage-1);
		model.addAttribute("curpage", page);
		model.addAttribute("orders", orders);

		return "orderHistory";
	}

	@RequestMapping("/changeStatus")
	public String changeStatus(@RequestParam("id") int id) {
		Order order = orderService.findByOrder_id(id);
		order.setStatus(true);
		orderService.save(order);
		return "redirect:/orderhistory";
	}
}

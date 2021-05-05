package com.onlinestore.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlinestore.domain.Category;
import com.onlinestore.domain.Product;
import com.onlinestore.domain.User;
import com.onlinestore.service.CategoryService;
import com.onlinestore.service.ProductService;
import com.onlinestore.service.UserService;

@Controller
public class SearchController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;

	@RequestMapping("/searchByCategory/{category}")
	public String searchByCategory(
			@PathVariable String category,
			Model model, Principal principal,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@ModelAttribute("sorttype") String sorttype
	/* @ModelAttribute("showtype") String showtype */
			) {
		
		if (principal!=null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		String classActiveCategory = "active"+category;
		classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
		classActiveCategory = classActiveCategory.replaceAll("&", "");
		model.addAttribute(classActiveCategory, true);
		
		/*
		 * int num; if (showtype.equals("")) { showtype = "12"; num =
		 * Integer.parseInt(showtype); }else { num = Integer.parseInt(showtype); }
		 */
		
		String optionActiveSort;
		Pageable pageRequest;
		if (sorttype.equalsIgnoreCase("3")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("price").descending());
			optionActiveSort = "t3";
		}else if (sorttype.equalsIgnoreCase("2")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("price").ascending());
			optionActiveSort = "t2";
		}else if (sorttype.equalsIgnoreCase("4")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("product_name").ascending());
			optionActiveSort = "t4";
		}else if (sorttype.equalsIgnoreCase("5")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("product_name").descending());
			optionActiveSort = "t5";
		}else {
			pageRequest = PageRequest.of(page, 12);
			optionActiveSort = "t1";
		}
		model.addAttribute(optionActiveSort, true);
		
		Page<Product> productList = productService.findByBigGroupPaginated(category, pageRequest);
		int totalPage = productList.getTotalPages();
		List<Integer> pages = new ArrayList<Integer>();
		if (totalPage==0) {
			pages.add(0);
		}else {
		for (int i = 0; i <totalPage ; i++) {
			pages.add(i);
			}
		}
		
		if (productList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "product";
		}
		
		model.addAttribute("productList", productList);
		model.addAttribute("pages", pages);
		model.addAttribute("totalpage", totalPage-1);
		model.addAttribute("curpage", page);
		
		return "product";
		
	}
	
	@RequestMapping("/searchByGroup/{group}")
	public String searchByGroup(
			@PathVariable String group,
			Model model, Principal principal,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@ModelAttribute("sorttype") String sorttype
			) {
		
		if (principal!=null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		String classActiveGroup = "active"+group;
		classActiveGroup = classActiveGroup.replaceAll("\\s+", "");
		classActiveGroup = classActiveGroup.replaceAll("&", "");
		model.addAttribute(classActiveGroup, true);
		/*
		 * classActiveGroup = classActiveGroup.replaceAll("\\s+", ""); classActiveGroup
		 * = classActiveGroup.replaceAll("&", "");
		 */
		
		String optionActiveSort;
		Pageable pageRequest;
		if (sorttype.equalsIgnoreCase("3")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("price").descending());
			optionActiveSort = "t3";
		}else if (sorttype.equalsIgnoreCase("2")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("price").ascending());
			optionActiveSort = "t2";
		}else if (sorttype.equalsIgnoreCase("4")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("product_name").ascending());
			optionActiveSort = "t4";
		}else if (sorttype.equalsIgnoreCase("5")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("product_name").descending());
			optionActiveSort = "t5";
		}else {
			pageRequest = PageRequest.of(page, 12);
			optionActiveSort = "t1";
		}
		model.addAttribute(optionActiveSort, true);
		
		Page<Product> productList = productService.findByCategoryPaginated(group, pageRequest);
		int totalPage = productList.getTotalPages();
		List<Integer> pages = new ArrayList<Integer>();
		if (totalPage==0) {
			pages.add(0);
		}else {
		for (int i = 0; i <totalPage ; i++) {
			pages.add(i);
			}
		}
		
		if (productList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "product";
		}
		
		model.addAttribute("productList", productList);
		model.addAttribute("pages", pages);
		model.addAttribute("totalpage", totalPage-1);
		model.addAttribute("curpage", page);
		
		return "product";
	}
	
	@RequestMapping("/searchProduct")
	public String searchProduct(
			@ModelAttribute("keyword") String keyword,
			Principal principal, Model model,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@ModelAttribute("sorttype") String sorttype
			) {
		
		if (principal!=null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
		}
		
		String optionActiveSort;
		Pageable pageRequest;
		if (sorttype.equalsIgnoreCase("3")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("price").descending());
			optionActiveSort = "t3";
		}else if (sorttype.equalsIgnoreCase("2")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("price").ascending());
			optionActiveSort = "t2";
		}else if (sorttype.equalsIgnoreCase("4")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("product_name").ascending());
			optionActiveSort = "t4";
		}else if (sorttype.equalsIgnoreCase("5")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("product_name").descending());
			optionActiveSort = "t5";
		}else {
			pageRequest = PageRequest.of(page, 12);
			optionActiveSort = "t1";
		}
		model.addAttribute(optionActiveSort, true);
		
		Page<Product> productList = productService.blurrySearchPaginated(keyword, pageRequest);
		int totalPage = productList.getTotalPages();
		List<Integer> pages = new ArrayList<Integer>();
		if (totalPage==0) {
			pages.add(0);
		}else {
		for (int i = 0; i <totalPage ; i++) {
			pages.add(i);
			}
		}
		
		if (productList.isEmpty()) {
			model.addAttribute("emptyList", true);
			return "product";
		}
		model.addAttribute("keyword", keyword);
		model.addAttribute("productList", productList);
		model.addAttribute("pages", pages);
		model.addAttribute("totalpage", totalPage-1);
		model.addAttribute("curpage", page);
		
		return "product";
		
	}
	
}

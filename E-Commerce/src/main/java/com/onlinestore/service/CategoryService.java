package com.onlinestore.service;

import java.util.List;

import com.onlinestore.domain.Category;
import com.onlinestore.domain.User;

public interface CategoryService {
	
	Category findByCategoryID(String categoryID);
	public Category save(Category category);
	
	public List<Category> findAll();
}

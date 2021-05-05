package com.onlinestore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinestore.domain.Category;
import com.onlinestore.domain.User;
import com.onlinestore.repository.CategoryRepository;
import com.onlinestore.repository.UserRepository;
import com.onlinestore.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category findByCategoryID(String categoryID) {
		return categoryRepository.findByCategoryID(categoryID);
	};
	
	public Category save(Category category) {
		return categoryRepository.save(category);
	}
	
	public List<Category> findAll(){
		return (List<Category>) categoryRepository.findAll();
	}
	
}


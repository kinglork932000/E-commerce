package com.onlinestore.repository;

import org.springframework.data.repository.CrudRepository;

import com.onlinestore.domain.Category;

public interface CategoryRepository extends CrudRepository<Category,Long> {
	Category findByCategoryID (String categoryID);
}
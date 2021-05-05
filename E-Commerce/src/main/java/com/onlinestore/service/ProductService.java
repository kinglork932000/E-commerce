package com.onlinestore.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.onlinestore.domain.Category;
import com.onlinestore.domain.Product;

public interface ProductService {

	Product save(Product product);
	void delete(Product product);
	List<Product> findAll();
	Product findByID(int product_id);
	List<Product> findByCategory(Category category);
	List<Product> findByBigGroup(String bigGroup);
	List<Product> randomProduct(int number);
	List<Product> randomProduct(Category category, int number);
	List<Product> randomProduct(String bigGroup, int number);
	
	List<Product> blurrySearch(String keyword);
	
	Page<Product> findPaginated(Pageable pageable);
	Page<Product> findByBigGroupPaginated(String bigGroup, Pageable pageable);
	Page<Product> blurrySearchPaginated(String keyword, Pageable pageable);
	Page<Product> findByCategoryPaginated(String category, Pageable pageable);
	
}

package com.onlinestore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.onlinestore.domain.Category;
import com.onlinestore.domain.Order;
import com.onlinestore.domain.Product;
import com.onlinestore.domain.User;

@Repository
public interface PaginationRepository extends PagingAndSortingRepository<Product, Long> {

	@Query(value = "SELECT * FROM products p WHERE p.categoryid IN (select categoryid from categories where biggroup = ?1)", nativeQuery = true)
	Page<Product> findByBigGroupPaginated(String bigGroup, Pageable pageable);
	
	@Query(value = "SELECT * FROM products p WHERE p.product_name LIKE %?1%", nativeQuery = true)
	Page<Product> blurrySearchPaginated(String keyword, Pageable pageable);
	
	@Query(value = "SELECT * FROM products p WHERE p.categoryid = ?1", nativeQuery = true)
	Page<Product> findByCategoryPaginated (String category, Pageable pageable);
	
	@Query(value = "SELECT * FROM products", nativeQuery = true)
	Page<Product> findPaginated(Pageable pageable);
	
}

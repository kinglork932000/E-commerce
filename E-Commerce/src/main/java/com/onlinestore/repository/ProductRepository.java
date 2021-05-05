package com.onlinestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.onlinestore.domain.Category;
import com.onlinestore.domain.Product;

public interface ProductRepository extends CrudRepository<Product,Long> {
	List<Product> findByCategory (Category category);
	@Query(value = "SELECT * FROM products p WHERE p.product_id = ?1", nativeQuery = true)
	Product findByProduct_id (int product_id);
	@Query(value = "SELECT * FROM products p WHERE p.categoryid IN (select categoryid from categories where biggroup = ?1)", nativeQuery = true)
	List<Product> findByBigGroup (String bigGroup);
	@Query(value="delete from products where product_id = ?1", nativeQuery = true)
	@Transactional
	@Modifying
	void deleteByID(int product_id);
	@Query(value = "SELECT * FROM products p WHERE p.product_name LIKE %?1%", nativeQuery = true)
	List<Product> findByproduct_nameContaining(String keyword);
}

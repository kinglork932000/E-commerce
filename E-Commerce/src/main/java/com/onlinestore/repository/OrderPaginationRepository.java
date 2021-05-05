package com.onlinestore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.onlinestore.domain.Order;

public interface OrderPaginationRepository extends PagingAndSortingRepository<Order, Long> {

	@Query(value = "SELECT * FROM orders", nativeQuery = true)
	Page<Order> findOrderPaginated(Pageable pageable);
	
	@Query(value = "SELECT * FROM orders o WHERE o.username = ?1", nativeQuery = true)
	Page<Order> findOrderByUserPaginated(String username ,Pageable pageable);
}

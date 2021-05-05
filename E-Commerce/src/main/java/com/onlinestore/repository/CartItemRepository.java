package com.onlinestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.onlinestore.domain.CartItem;
import com.onlinestore.domain.User;

public interface CartItemRepository extends CrudRepository<CartItem, Long>{
	List<CartItem> findByUser(User user);
	@Query(value="select * from cartitems where cartitem_id = ?1", nativeQuery = true)
	CartItem findByID(int cartitem_id);
	@Query(value="delete from cartitems where cartitem_id = ?1", nativeQuery = true)
	@Transactional
	@Modifying
	void deleteByID(int cartitem_id);
}
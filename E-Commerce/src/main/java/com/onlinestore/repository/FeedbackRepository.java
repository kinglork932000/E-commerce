package com.onlinestore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.onlinestore.domain.Feedback;
import com.onlinestore.domain.Product;

public interface FeedbackRepository extends PagingAndSortingRepository<Feedback,Long>  {

	@Query(value = "SELECT * FROM feedbacks", nativeQuery = true)
	Page<Feedback> findAllPaginated(Pageable pageable);
}

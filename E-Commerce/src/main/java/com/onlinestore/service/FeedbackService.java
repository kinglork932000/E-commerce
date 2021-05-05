package com.onlinestore.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.onlinestore.domain.Feedback;

public interface FeedbackService {
	Feedback save(Feedback feedback);
	List<Feedback> selectAll();
	
	Page<Feedback> findAllPaginated(Pageable pageable);
}

package com.onlinestore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlinestore.domain.Feedback;
import com.onlinestore.repository.FeedbackRepository;
import com.onlinestore.service.FeedbackService;


@Service
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	FeedbackRepository feedbackRepository;
	
	public Feedback save(Feedback feedback) {
		return (Feedback)feedbackRepository.save(feedback);
	}
	
	public List<Feedback> selectAll(){
		return (List<Feedback>) feedbackRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Feedback> findAllPaginated(Pageable pageable) {
		return feedbackRepository.findAllPaginated(pageable);
	};
}

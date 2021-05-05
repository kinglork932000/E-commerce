package com.onlinestore.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlinestore.domain.Feedback;
import com.onlinestore.service.FeedbackService;

@Controller
public class FeedbackController {
	@Autowired
	FeedbackService feedbackService;
	
	@RequestMapping("/feedback")
	public String feedback(@ModelAttribute("feedback") Feedback feedback, Model model) {
		feedback = feedbackService.save(feedback);
		model.addAttribute("feedbacked", true);
		return "contact";
	}
	
	@RequestMapping("/feedbackList")
	public String fbList(Model model, Principal principal,
			@RequestParam(name = "page", defaultValue = "0") int page, @ModelAttribute("sorttype") String sorttype) {
		
		String optionActiveSort;
		Pageable pageRequest;
		if (sorttype.equalsIgnoreCase("2")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("name").ascending());
			optionActiveSort = "t2";
		} else if (sorttype.equalsIgnoreCase("3")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("name").descending());
			optionActiveSort = "t3";
		} else if (sorttype.equalsIgnoreCase("4")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("date").ascending());
			optionActiveSort = "t4";
		} else if (sorttype.equalsIgnoreCase("5")) {
			pageRequest = PageRequest.of(page, 12, Sort.by("date").descending());
			optionActiveSort = "t5";
		} else {
			pageRequest = PageRequest.of(page, 12);
			optionActiveSort = "t1";
		}
		model.addAttribute(optionActiveSort, true);
		
		Page<Feedback> feedbacks = feedbackService.findAllPaginated(pageRequest);
		int totalPage = feedbacks.getTotalPages();
		List<Integer> pages = new ArrayList<Integer>();
		if (totalPage==0) {
			pages.add(0);
		}else {
		for (int i = 0; i <totalPage ; i++) {
			pages.add(i);
			}
		}
		model.addAttribute("totalpage", totalPage-1);
		model.addAttribute("curpage", page);
		model.addAttribute("pages", pages);
		model.addAttribute("feedbacks", feedbacks);
		return "feedbackList";
	}
}

package com.jojo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jojo.service.AtouUserProgressService;

@Controller
@RequestMapping("/atouUserProgress")
public class AtouUserProgressController {
	
	private static final String USER_PROGRESS = "user/userProgress";

	@Autowired
	private AtouUserProgressService atouUserProgressService;

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public String showUserProgress(@PathVariable(name = "userId") Long userId) {
		return USER_PROGRESS;
	}

}

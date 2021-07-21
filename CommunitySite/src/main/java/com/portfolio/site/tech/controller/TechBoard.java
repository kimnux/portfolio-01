package com.portfolio.site.tech.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tech")
public class TechBoard {
	
	@GetMapping("/list")
	public String list() {
		return "board/tech/list";
	}
	
	@GetMapping("/write")
	public String write(HttpServletRequest req) {
		if(req.getSession().getAttribute("user_info") == null) {
			return "redirect:/";
		}
		
		return "board/tech/write";
	}
}


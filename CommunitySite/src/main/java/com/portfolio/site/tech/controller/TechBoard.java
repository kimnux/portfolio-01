package com.portfolio.site.tech.controller;

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
}

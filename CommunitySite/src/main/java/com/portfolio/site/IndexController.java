package com.portfolio.site;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @author dongwook
 *
 * 2021.07.11
 */
@Controller
@RequestMapping("/")
@Slf4j
public class IndexController {
	
	@GetMapping("/")
	public String home(HttpServletRequest req) {
		return "main";
	}
	
}

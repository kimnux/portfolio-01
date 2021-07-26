package com.portfolio.site.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.site.category.service.CategoryService;
import com.portfolio.site.category.vo.CategoryVO;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	 
	/**
	 * 
	 * @return
	 * 
	 * 2021.07.20
	 */
	@GetMapping("/list")
	@ResponseBody
	public List<CategoryVO> categoryList() {
		List<CategoryVO> list = categoryService.categoryList();
		return list;
	}
}

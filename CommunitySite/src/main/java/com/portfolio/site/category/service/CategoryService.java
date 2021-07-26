package com.portfolio.site.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.site.category.mapper.CategoryMapper;
import com.portfolio.site.category.vo.CategoryVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	
	/**
	 * 
	 * @return
	 * 
	 * 2021.07.20
	 */
	public List<CategoryVO> categoryList() {
		List<CategoryVO> list = categoryMapper.categoryList();
		log.info("categoryList : " + list);
		return list;
	}
}

package com.portfolio.site.tech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.site.tech.mapper.TechBoardMapper;
import com.portfolio.site.tech.vo.TechBoardVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TechBoardService {
	
	@Autowired
	private TechBoardMapper techBoardMapper;
	
	@Transactional
	public void write(TechBoardVO params) {
		try {
			techBoardMapper.write(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<TechBoardVO> techList() {
		List<TechBoardVO> list = null;
		try {
			list = techBoardMapper.techList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}

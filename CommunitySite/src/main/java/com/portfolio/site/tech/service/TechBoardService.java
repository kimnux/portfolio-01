package com.portfolio.site.tech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.site.common.util.Paging;
import com.portfolio.site.tech.mapper.TechBoardMapper;
import com.portfolio.site.tech.vo.TechBoardVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TechBoardService {
	
	@Autowired
	private TechBoardMapper techBoardMapper;
	
	/**
	 * 
	 * @param params
	 * 
	 * 2021.07.24
	 */
	@Transactional
	public void write(TechBoardVO params) {
		try {
			techBoardMapper.write(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param blockSize
	 * @return
	 * 
	 * 2021.07.24
	 */
	public Paging<TechBoardVO> techList(int currentPage, int pageSize, int blockSize) {
		List<TechBoardVO> list = null;
		Paging<TechBoardVO> paging = null;
		try {
			int totalCount = techBoardMapper.totalCount();
			paging = new Paging<TechBoardVO>(totalCount, currentPage, pageSize, blockSize);
			
			Map<String, Integer> map = new HashMap<>();
			map.put("startNo", paging.getStartNo());
			map.put("pageSize", paging.getPageSize());
			
			list = techBoardMapper.techList(map);
			paging.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paging;
	}
	
	/**
	 * 
	 * @param idx
	 * @return
	 * 
	 * 2021.07.25
	 */
	public TechBoardVO techDetail(int idx) {
		TechBoardVO detail = null;
		try {
			detail = techBoardMapper.techDetail(idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detail;
	}
	
	/**
	 * 
	 * @param params
	 * 
	 * 2021.07.27
	 */
	@Transactional
	public void techUpdate(TechBoardVO params) {
		try {
			techBoardMapper.techUpdate(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

package com.portfolio.site.tech.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.site.common.util.Paging;
import com.portfolio.site.tech.mapper.TechBoardMapper;
import com.portfolio.site.tech.vo.GoodVO;
import com.portfolio.site.tech.vo.TechBoardVO;
import com.portfolio.site.tech.vo.TechReplyVO;

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
	
	public List<TechReplyVO> techReplyList(int board_idx) {
		List<TechReplyVO> list = null;
		try {
			list = techBoardMapper.techReplyList(board_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @param params
	 * 
	 * 2021.07.31
	 */
	@Transactional
	public void replyWrite(TechReplyVO params) {
		try {
			techBoardMapper.replyWrite(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param goodVO
	 * @return
	 * 
	 * 2021.08.15
	 */
	public GoodVO selectTechBoardGood(GoodVO goodVO) {
		GoodVO vo = null;
		
		try {
			vo = techBoardMapper.selectTechBoardGood(goodVO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	public String updateTechBoardGood(GoodVO goodVO) {
		try {
			GoodVO vo = techBoardMapper.selectTechBoardGood(goodVO);
			
			if(vo.getCount() == 0) {
				goodVO.setIsGood(1);
				techBoardMapper.insertTechBoardGood(goodVO);
				
				return "1";
			} else if(vo.getCount() == 1) {
				//update
				switch(vo.getIsGood()) {
				case 0: goodVO.setIsGood(1); break;
				case 1: goodVO.setIsGood(0); break;
				}
				
				techBoardMapper.updateTechBoardGood(goodVO);
				return goodVO.getIsGood()+"";
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}

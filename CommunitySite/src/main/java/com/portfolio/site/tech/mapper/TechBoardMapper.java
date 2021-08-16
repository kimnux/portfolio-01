package com.portfolio.site.tech.mapper;

import java.util.List;
import java.util.Map;

import com.portfolio.site.tech.vo.GoodVO;
import com.portfolio.site.tech.vo.TechBoardVO;
import com.portfolio.site.tech.vo.TechReplyVO;

public interface TechBoardMapper {
	
	public void write(TechBoardVO params) throws Exception;
	public List<TechBoardVO> techList(Map<String, Integer> map) throws Exception;
	public TechBoardVO techDetail(int idx) throws Exception;
	public int totalCount() throws Exception;
	public void techUpdate(TechBoardVO params) throws Exception;
	
	public List<TechReplyVO> techReplyList (int board_idx) throws Exception;
	public void replyWrite(TechReplyVO params) throws Exception;
	
	public GoodVO selectTechBoardGood(GoodVO goodVO) throws Exception;
	public void insertTechBoardGood(GoodVO goodVO) throws Exception;
	public void updateTechBoardGood(GoodVO goodVO) throws Exception;
}

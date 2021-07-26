package com.portfolio.site.tech.mapper;

import java.util.List;
import java.util.Map;

import com.portfolio.site.tech.vo.TechBoardVO;

public interface TechBoardMapper {
	
	public void write(TechBoardVO params) throws Exception;
	public List<TechBoardVO> techList(Map<String, Integer> map) throws Exception;
	public TechBoardVO techDetail(int idx) throws Exception;
	public int totalCount() throws Exception;
	public void techUpdate(TechBoardVO params) throws Exception;
}

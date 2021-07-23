package com.portfolio.site.tech.mapper;

import java.util.List;

import com.portfolio.site.tech.vo.TechBoardVO;

public interface TechBoardMapper {
	
	public void write(TechBoardVO params) throws Exception;
	public List<TechBoardVO> techList() throws Exception;
	
}

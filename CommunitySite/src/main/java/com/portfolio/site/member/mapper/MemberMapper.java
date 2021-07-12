package com.portfolio.site.member.mapper;

import com.portfolio.site.member.vo.MemberVO;

public interface MemberMapper {
	public void join(MemberVO memberVO);
	public MemberVO login(MemberVO memberVO) throws Exception;
}

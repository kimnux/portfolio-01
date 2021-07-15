package com.portfolio.site.member.mapper;

import com.portfolio.site.member.vo.MemberVO;

/**
 * 
 * @author dongwook
 * 
 * 2021.07.12
 */
public interface MemberMapper {
	/**
	 * 
	 * @param memberVO
	 * 
	 * 2021.07.12
	 */
	public void join(MemberVO memberVO);
	
	/**
	 * 
	 * @param memberVO
	 * @return MemberVO
	 * @throws Exception
	 * 
	 * 2021.07.12
	 */
	public MemberVO login(MemberVO memberVO) throws Exception;
	
	/**
	 * 
	 * @param memberVO
	 * @return int
	 * @throws Exception
	 */
	public int loginFail(MemberVO memberVO) throws Exception;
}

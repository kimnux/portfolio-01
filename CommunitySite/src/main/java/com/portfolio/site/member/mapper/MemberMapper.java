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
	 * @throws Exception
	 * 
	 * 2021.07.15
	 */
	public void loginFail(MemberVO memberVO) throws Exception;
	
	/**
	 * 
	 * @param userId
	 * @throws Exception
	 * 
	 * 2021.07.16
	 */
	public void failCntReset(String userId) throws Exception;
	
	/**
	 * 
	 * @param userId
	 * @return failCnt(int)
	 * @throws Exception
	 * 
	 * 2021.07.16
	 */
	public int selectFailCnt(String userId) throws Exception;
	
	
	
}

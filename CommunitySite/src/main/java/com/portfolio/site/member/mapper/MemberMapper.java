package com.portfolio.site.member.mapper;

import org.apache.ibatis.annotations.Param;

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
	public int join(MemberVO memberVO) throws Exception;
	
	/**
	 * 
	 * @param memberVO
	 * @return MemberVO
	 * @throws Exception
	 * 
	 * 2021.07.12
	 */
	public MemberVO login(@Param("userId") String userId, @Param("password") String password) throws Exception;
	
	/**
	 * 
	 * @param password
	 * @return String
	 * @throws Exception
	 */
	public String userPwd(String password) throws Exception;
	
	/**
	 * 
	 * @param memberVO
	 * @throws Exception
	 * 
	 * 2021.07.15
	 */
	public void loginFail(String userId) throws Exception;
	
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
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 * 
	 * 2021.07.24
	 */
	public String selectUserId(String userId) throws Exception;
	
	
}

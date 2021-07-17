package com.portfolio.site.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.site.member.mapper.MemberMapper;
import com.portfolio.site.member.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author dongwook
 * 
 * 2021.07.12
 */
@Service
@Slf4j
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	/**
	 * 
	 * @param param
	 * @return MemberVO
	 * 
	 * 2021.07.12
	 */
	public MemberVO login(MemberVO param) {
		MemberVO user = null;
		
		try {
			user = memberMapper.login(param);

			if( user == null ) {
				memberMapper.loginFail(param);
			}else {
				memberMapper.failCntReset(user.getUserId());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	/**
	 * 
	 * @param userId
	 * @return failCnt(int)
	 * 
	 * 2021.07.16
	 */
	public int selectFailCnt(String userId) {
		int failCnt = 0;
		
		try {
			failCnt = memberMapper.selectFailCnt(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return failCnt;
	}
	
	/**
	 * 
	 * @param memberVO
	 * 
	 * 2021.07.17
	 */
	public int join(MemberVO memberVO) {
		int result = 0;
		try {
			result = memberMapper.join(memberVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}

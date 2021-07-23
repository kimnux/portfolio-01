package com.portfolio.site.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public MemberVO login(String userId, String password) {
		
		MemberVO user = null;
		try {
			// 로그인시 디비에 저장된 문자열(암호화된 문자열)과 사용자가 입력한 비밀번호 검증
			String encrypted = memberMapper.userPwd(userId);
			
			if( encrypted != null ) {
				boolean isUser = BCrypt.checkpw(password, encrypted);
				
				if( isUser ) {
					user = memberMapper.login(userId, encrypted);
				}
			}
			
			if( user == null ) {
				memberMapper.loginFail(userId);
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
	 * @param memberVO
	 * 
	 * 2021.07.17
	 */
	@Transactional
	public int join(MemberVO memberVO) {
		
		// 디비에 저장할 비밀번호 암호화
        String encrypted = BCrypt.hashpw(memberVO.getPassword(), BCrypt.gensalt());
		memberVO.setPassword(encrypted);
		
		int result = 0;
		try {
			result = memberMapper.join(memberVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}

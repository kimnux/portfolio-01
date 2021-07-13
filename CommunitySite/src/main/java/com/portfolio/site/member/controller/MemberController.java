package com.portfolio.site.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.site.member.service.MemberService;
import com.portfolio.site.member.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author dongwook
 * 
 * 2021.07.12
 */
@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 
	 * @param req
	 * @return String
	 * 
	 * 2021.07.12
	 */
	@GetMapping("/login")
	public String login(HttpServletRequest req) {
		return "member/login";
	}
	
	/**
	 * 
	 * @param req
	 * @param memberVO
	 * @return boolean
	 * 
	 * 2021.07.12
	 */
	@PostMapping("/loginOk")
	@ResponseBody
	public boolean loginOk(HttpServletRequest req, MemberVO memberVO) {
		MemberVO user = memberService.login(memberVO);

		if(user == null) {
			return false;
		}else {
			req.getSession().setAttribute("user_info", user);
			return true;
		}
		
	}
	
	
}

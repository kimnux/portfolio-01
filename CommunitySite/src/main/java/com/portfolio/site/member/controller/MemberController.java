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

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/login")
	public String login(HttpServletRequest req) {
		return "member/login";
	}
	
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

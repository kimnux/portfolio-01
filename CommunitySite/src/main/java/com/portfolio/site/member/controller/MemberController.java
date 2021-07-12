package com.portfolio.site.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/loginOk")
	public String loginOk(HttpServletRequest req, MemberVO memberVO) {
		MemberVO user = memberService.login(memberVO);
		log.info("user : "+user);
		
		//req.getSession().setAttribute("user_info", user);
		return "main";
	}
	
	
}

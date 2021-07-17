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
		if( req.getSession().getAttribute("user_info") != null ) {
			return "redirect:/";
		}
		return "member/login";
	}
	
	/**
	 * 
	 * @param req
	 * @return String
	 * 2021.07.15
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		req.getSession().removeAttribute("user_info");
		return "redirect:/member/login";
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
	public int loginOk(HttpServletRequest req, MemberVO memberVO) {
		int failCnt = memberService.selectFailCnt(memberVO.getUserId());
		
		if( failCnt >= 5 ) {
			return 0;
		}

		MemberVO user = memberService.login(memberVO);
		if( user == null ) {
			return -1;
		}else {
			req.getSession().setAttribute("user_info", user);
			return 1;
		}
	}
	
	/**
	 * 
	 * @param req
	 * @return String
	 * 
	 * 2021.07.17
	 */
	@GetMapping("/joinPage")
	public String joinPage(HttpServletRequest req) {
		if( req.getSession().getAttribute("user_info") != null ) {
			return "redirect:/";
		}
		return "member/join";
	}
	
	@PostMapping("/joinOk")
	@ResponseBody
	public int joinOk(MemberVO memberVO) {
		int result = memberService.join(memberVO);
		log.info("joinOk - result : "+result);
		return result;
	}
	
}

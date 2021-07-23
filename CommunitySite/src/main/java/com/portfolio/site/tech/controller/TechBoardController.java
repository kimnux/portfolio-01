package com.portfolio.site.tech.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portfolio.site.member.vo.MemberVO;
import com.portfolio.site.tech.service.TechBoardService;
import com.portfolio.site.tech.vo.TechBoardVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/tech")
@Slf4j
public class TechBoardController {
	
	@Autowired
	private TechBoardService techBoardService;
	
	@GetMapping("/list")
	public String list() {
		return "board/tech/list";
	}
	
	@GetMapping("/write")
	public String write(HttpServletRequest req) {
		if(req.getSession().getAttribute("user_info") == null) {
			return "redirect:/";
		}
		
		return "board/tech/write";
	}
	
	@PostMapping("/writeOk")
	public String writeOk(HttpServletRequest req, TechBoardVO techBoardVO) {
		MemberVO session = (MemberVO) req.getSession().getAttribute("user_info");

		if(session == null) {
			return "redirect:/";
		}
		//TODO: 저장 로직
		techBoardVO.setWriter(session.getUserId());
		techBoardService.write(techBoardVO);
		//
		
		return "board/tech/list";
	}
	
}


package com.portfolio.site.tech.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.portfolio.site.common.util.Paging;
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
	public String list(Model model,@RequestParam(defaultValue = "1", value = "p", required = false) int currentPage, 
			 @RequestParam(defaultValue = "5", value = "s", required = false) int pageSize, 
			 @RequestParam(defaultValue = "5", value = "b", required = false) int blockSize) {
		
		Paging<TechBoardVO> paging = techBoardService.techList(currentPage,pageSize,blockSize);
		model.addAttribute("paging",paging);
		
		return "board/tech/list";
	}
	
	@GetMapping("/write")
	public String write(HttpServletRequest req) {
		if(req.getSession().getAttribute("user_info") == null) {
			return "redirect:/member/login";
		}
		
		return "board/tech/write";
	}
	
	@PostMapping("/writeOk")
	public String writeOk(HttpServletRequest req, TechBoardVO techBoardVO, String title, String content) {
		MemberVO session = (MemberVO) req.getSession().getAttribute("user_info");

		if(session == null) {
			return "redirect:/";
		}
		
		techBoardVO.setWriter(session.getUserId());
		try {
			techBoardService.write(techBoardVO);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/tech/list";
	}
	
	@GetMapping("/detail")
	public String detail(int idx, Model model) {
		TechBoardVO detail = techBoardService.techDetail(idx);
		model.addAttribute("detail", detail);
		return "board/tech/detail";
	}
	

	
}


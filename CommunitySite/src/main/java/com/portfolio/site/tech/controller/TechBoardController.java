package com.portfolio.site.tech.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.site.common.util.KakaoAPI;
import com.portfolio.site.common.util.Paging;
import com.portfolio.site.member.vo.MemberVO;
import com.portfolio.site.tech.service.TechBoardService;
import com.portfolio.site.tech.vo.TechBoardVO;
import com.portfolio.site.tech.vo.TechReplyVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/tech")
@Slf4j
public class TechBoardController {
	
	@Autowired
	private TechBoardService techBoardService;
	
	@Autowired
	private KakaoAPI kakao;
	
	/**
	 * 
	 * @param model
	 * @param currentPage
	 * @param pageSize
	 * @param blockSize
	 * @return
	 * 
	 * 2021.07.24
	 */
	@GetMapping("/list")
	public String list(
			Model model,@RequestParam(defaultValue = "1", value = "p", required = false) int currentPage, 
			 @RequestParam(defaultValue = "5", value = "s", required = false) int pageSize, 
			 @RequestParam(defaultValue = "5", value = "b", required = false) int blockSize) {
		
		Paging<TechBoardVO> paging = techBoardService.techList(currentPage,pageSize,blockSize);
		model.addAttribute("paging",paging);
		
		return "board/tech/list";
	}

	/**
	 * 
	 * @param req
	 * @return
	 * 
	 * 2021.07.24
	 */
	@GetMapping("/write")
	public String write(HttpServletRequest req) {
		String kakaoToken = (String)req.getSession().getAttribute("kakaoToken");
		
		if(req.getSession().getAttribute("user_info") == null && kakaoToken == null) {
			return "redirect:/member/login";
		}
		
		return "board/tech/write";
	}
	
	/**
	 * 
	 * @param req
	 * @param techBoardVO
	 * @param title
	 * @param content
	 * @return
	 * 
	 * 2021.07.24
	 */
	@PostMapping("/writeOk")
	public String writeOk(HttpServletRequest req, TechBoardVO techBoardVO, String title, String content) {
		MemberVO session = (MemberVO) req.getSession().getAttribute("user_info");
		String kakaoToken = (String)req.getSession().getAttribute("kakaoToken");
		
		if(session == null && kakaoToken == null) {
			return "redirect:/";
		}
		if(session != null) {
			techBoardVO.setWriter(session.getUserId());
		}else if(kakaoToken != null) {
			HashMap<String, Object> userInfo = kakao.getUserInfo(kakaoToken);
			techBoardVO.setWriter((String) userInfo.get("nickname"));
		}
		
		try {
			techBoardService.write(techBoardVO);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/tech/list";
	}
	
	/**
	 * 
	 * @param idx
	 * @param model
	 * @return
	 * 
	 * 2021.07.25
	 */
	@GetMapping("/detail")
	public String detail(HttpServletRequest req, int idx, Model model) {
		MemberVO session = (MemberVO) req.getSession().getAttribute("user_info");
		String kakaoToken = (String)req.getSession().getAttribute("kakaoToken");
		TechBoardVO detail = techBoardService.techDetail(idx);

		model.addAttribute("detail", detail);
		if(session != null) {
			model.addAttribute("session",session);
		}
		if(kakaoToken != null) {
			HashMap<String, Object> userInfo = kakao.getUserInfo(kakaoToken);
			model.addAttribute("userInfo",userInfo);
		}
		return "board/tech/detail";
	}
	
	/**
	 * 
	 * @param req
	 * @param idx
	 * @param model
	 * @return
	 * 
	 * 2021.07.26
	 */
	@PostMapping("/edit")
	public String edit(HttpServletRequest req, int idx, Model model) {
		MemberVO session = (MemberVO) req.getSession().getAttribute("user_info");
		String kakaoToken = (String)req.getSession().getAttribute("kakaoToken");
		
		if(session == null && kakaoToken == null) {
			return "redirect:/member/login";
		}
		
		TechBoardVO detail = techBoardService.techDetail(idx);
		model.addAttribute("detail", detail);
		model.addAttribute("flag","edit");
		return "board/tech/write";
	}
	
	/**
	 * 
	 * @param idx
	 * @return
	 * 
	 * 2021.07.27
	 */
	@PostMapping("/editOk")
	public String editOk(TechBoardVO params) {
		techBoardService.techUpdate(params);
		
		return "redirect:/tech/detail?idx="+params.getIdx();
	}
	
	@GetMapping("/replyList")
	@ResponseBody
	public List<TechReplyVO> techReplyList(int board_idx) {
		List<TechReplyVO> list = techBoardService.techReplyList(board_idx);
		log.info("list : " + list);
		
		return list;
	}
	
	/**
	 * 
	 * @param params
	 * 
	 * 2021.07.31
	 */
	@PostMapping("/replyOk")
	@ResponseBody
	public void replyOk(HttpServletRequest req, TechReplyVO params) {
		MemberVO session = (MemberVO) req.getSession().getAttribute("user_info");
		String kakaoToken = (String)req.getSession().getAttribute("kakaoToken");
		
		if(session != null) {
			params.setWriter(session.getUserId());
		}
		if(kakaoToken != null) {
			HashMap<String, Object> userInfo = kakao.getUserInfo(kakaoToken);
			params.setWriter((String)userInfo.get("nickname"));
		}
		
		
		techBoardService.replyWrite(params);
	}
	
}


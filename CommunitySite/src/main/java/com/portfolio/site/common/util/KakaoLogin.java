package com.portfolio.site.common.util;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KakaoLogin {

	@Autowired
	private KakaoAPI kakao;

	@GetMapping("/getKakaoAuthUrl")
	public @ResponseBody String getKakaoAuthUrl(HttpServletRequest request) throws Exception {
		String reqUrl = "https://kauth.kakao.com/oauth/authorize" + "?client_id="
				+ "&redirect_uri=http://localhost:8080/CommunitySite/kakao_oauth" + "&response_type=code";

		return reqUrl;
	}
	
	@GetMapping("/kakao_oauth")
	public String oauthKakao(@RequestParam(value = "code", required = false) String code, Model model, HttpServletRequest req)
			throws Exception {

		if(code != null) {
			System.out.println("#########" + code);
			
			String access_Token = kakao.getAccessToken(code);
			System.out.println("###access_Token#### : " + access_Token);
			req.getSession().setAttribute("kakaoToken", access_Token);// 토근 저장
			
			HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
			req.getSession().setAttribute("userInfo", userInfo); // 사용자정보 세션저장

			System.out.println("###access_Token#### : " + access_Token);
//			System.out.println("###userInfo#### : " + userInfo.get("email"));
			System.out.println("###nickname#### : " + userInfo.get("nickname"));
			
//			model.addAttribute("userInfo", userInfo);
		}

		return "main"; // 본인 원하는 경로 설정
	}

	// 카카오 로그아웃
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		String token = (String) req.getSession().getAttribute("kakaoToken");
		kakao.getLogout(token);
		
		req.getSession().removeAttribute("userInfo"); // 사용자정보 세션저장
		return "redirect:/";
	}

}

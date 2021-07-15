package com.portfolio.site.member.vo;

import lombok.Data;

@Data
public class MemberVO {
	private int idx;
	private String userId;
	private String password;
	private String nickName;
	private String grade;
	
	private String regDt;
	private int failCnt;
}

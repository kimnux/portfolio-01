package com.portfolio.site.tech.vo;

import java.util.Date;

import lombok.Data;

@Data
public class TechBoardVO {
	private int idx;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
}

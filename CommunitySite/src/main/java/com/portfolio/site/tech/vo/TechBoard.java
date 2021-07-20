package com.portfolio.site.tech.vo;

import java.util.Date;

import lombok.Data;

@Data
public class TechBoard {
	private int idx;
	private String title;
	private String content;
	private boolean good;
	private Date regDate;
}

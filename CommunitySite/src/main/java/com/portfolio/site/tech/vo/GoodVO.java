package com.portfolio.site.tech.vo;

import java.util.Date;

import lombok.Data;

@Data
public class GoodVO {
	private int idx;
	private int board_idx;
	private String userId;
	private int isGood;
	private Date regDate;
}

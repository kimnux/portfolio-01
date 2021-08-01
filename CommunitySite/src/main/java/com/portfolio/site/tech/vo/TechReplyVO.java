package com.portfolio.site.tech.vo;

import java.util.Date;

import lombok.Data;

@Data
public class TechReplyVO {
	private int idx;
	private int board_idx;
	private String reply_content;
	private int reply_good;
	private String writer;
	private Date regDate;
}

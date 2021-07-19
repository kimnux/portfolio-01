package com.portfolio.site.category.vo;

import lombok.Data;

@Data
public class CategoryVO {
	private int idx;
	private String categoryNm;
	private String categoryNo;
	private int depth;
	private String url;
}

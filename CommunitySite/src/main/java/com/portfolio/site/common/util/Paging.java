package com.portfolio.site.common.util;

import java.util.List;

public class Paging<T> {
	// 입력받을 변수 4개
		private int totalCount;			// 전체 개수
		private int currentPage;		// 현재 페이지
		private int pageSize;			// 페이지당 글 수
		private int blockSize;			// 하단표시 페이지 갯수

		// 계산할 변수 5개
		private int totalPage;			// 전체 페이지 수
		private int startNo;			// 시작 글번호
		private int endNo;				// 끝 글번호(오라클만 사용)
		private int startPage;			// 시작 페이지 번호
		private int endPage;			// 끝 페이지 번호
		
		// 1페이지 분량의 VO 데이터를 담을 변수
		private List<T> list;			// 여기에 DB에서 1페이지 분량을 얻어서 담는다.

		// 입력변수는 생성자로 받자!!!
		public Paging(int totalCount, int currentPage, int pageSize, int blockSize) {
			super();
			this.totalCount = totalCount;
			this.currentPage = currentPage;
			this.pageSize = pageSize;
			this.blockSize = blockSize;
			// 나머지 변수는 계산을 하자!!!
			calc();
		}
		private void calc() {
			// 일단은 입력된 변수의 유효성을 검증하자!!!
			if(currentPage<=0) currentPage =1; // 현재페이지가 0이하일 수 없다.
			if(pageSize<1) pageSize = 10; // 페이지당 글수는 1보다 적을 수 없다
			if(blockSize<1) blockSize = 10; // 페이지 리스트는 1보다 적을 수 없다
			if(totalCount>0) {
				totalPage = (totalCount-1)/pageSize + 1;
				// 현재 페이지는 전체 페이지 수보다 클 수 없다
				if(currentPage>totalPage) currentPage = 1;
				startNo = (currentPage-1)*pageSize; // 오라클은 +1을 해준다.
				endNo = startNo + pageSize -1;
				//끝 글번호는 전체 개수를 넘을 수 없다.
				if(endNo>totalCount) endNo = totalCount; // 오라클에서만 사용
				startPage = (currentPage-1)/blockSize * blockSize + 1;
				endPage = startPage + blockSize - 1;
				// 마지막 페이지 번호는 전체 페이지 보다 클 수 없다.
				if(endPage>totalPage) endPage = totalPage;
			}
		}

		// list를 뺀 모든 멤버는 getter만 만들어 준다.
		public List<T> getList() {
			return list;
		}
		public void setList(List<T> list) {
			this.list = list;
		}
		public int getTotalCount() {
			return totalCount;
		}
		public int getCurrentPage() {
			return currentPage;
		}
		public int getPageSize() {
			return pageSize;
		}
		public int getBlockSize() {
			return blockSize;
		}
		public int getTotalPage() {
			return totalPage;
		}
		public int getStartNo() {
			return startNo;
		}
		public int getEndNo() {
			return endNo;
		}
		public int getStartPage() {
			return startPage;
		}
		public int getEndPage() {
			return endPage;
		}
		
		@Override
		public String toString() {
			return "Paging [totalCount=" + totalCount + ", currentPage=" + currentPage + ", pageSize=" + pageSize
					+ ", blockSize=" + blockSize + ", totalPage=" + totalPage + ", startNo=" + startNo + ", endNo=" + endNo
					+ ", startPage=" + startPage + ", endPage=" + endPage + ", list=" + list + "]";
		}
		//---------------------------------------------------------------------------------------------------------
		// 페이지 상단에 페이지 정보를 출력해주는 메서드 작성
		public String getPageInfo() {
			String msg = "전체 : " + totalCount + "개";
			if(totalCount>0) {
				msg += "(" + currentPage + "/" + totalPage + "Page)"; 
			}
			return msg;
		}
		
		/**
		 * POST 페이징
		 * @return
		 */
		// 페이지 하단에 페이지 번호 링크 출력해주는 메서드 작성
		public String getPageList() {
			StringBuilder sb = new StringBuilder();
			sb.append("<ul class='pagination pagination-sm justify-content-center'>");
			// 이전은 시작페이지 번호가 1보다 클때만 있다.
			if(startPage>1) {
				sb.append("<li class='page-item'>");
				sb.append("<a class='page-link' href='javascript:sendPost(\"?\",{\"p\":"+(startPage-1)+",\"s\":"+pageSize+",\"b\":"+blockSize+"});' aria-label='Previous'>");
				sb.append("<span aria-hidden='true'>&laquo;</span>");
				sb.append("<span class='sr-only'>Previous</span>");
				sb.append("</a>");
				sb.append("</li>");
			}
			// 페이지 번호 반복
			for(int i=startPage;i<=endPage;i++) {
				// 현재 페이지는 링크를 걸지 않는다.
				if(i==currentPage) {
					sb.append("<li class='page-item active'><span class='page-link'>" + i + "</span></li>");
				}else {
					sb.append("<li class='page-item'><a class='page-link' href='javascript:sendPost(\"?\",{\"p\":"+(i)+",\"s\":"+pageSize+",\"b\":"+blockSize+"});'>" + i + "</a></li>");
				}
			}
			// 다음은 끝페이지 번호가 전체페이지 번호 보다 적을때만 있다.
			if(endPage<totalPage) {
				sb.append("<li class='page-item'>");
				sb.append("<a class='page-link' href='javascript:sendPost(\"?\",{\"p\":"+(endPage+1)+",\"s\":"+pageSize+",\"b\":"+blockSize+"});' aria-label='Next'>");
				sb.append("<span aria-hidden='true'>&raquo;</span>");
				sb.append("<span class='sr-only'>Next</span>");
				sb.append("</a>");
				sb.append("</li>");
			}
			sb.append("</ul>");
			return sb.toString();
		}
		
		/**
		 * GET 페이징
		 * @return
		 */
		public String getPageList2() {
			StringBuilder sb = new StringBuilder();
			sb.append("<ul class='pagination pagination-sm justify-content-center'>");
			// 이전은 시작페이지 번호가 1보다 클때만 있다.
			if(startPage>1) {
				sb.append("<li class='page-item'>");
				sb.append("<a class='page-link' href='?p="+(startPage-1)+"&s="+pageSize+"&b="+blockSize+"' aria-label='Previous'>");
				sb.append("<span aria-hidden='true'>&laquo;</span>");
				sb.append("<span class='sr-only'>Previous</span>");
				sb.append("</a>");
				sb.append("</li>");
			}
			// 페이지 번호 반복
			for(int i=startPage;i<=endPage;i++) {
				// 현재 페이지는 링크를 걸지 않는다.
				if(i==currentPage) {
					sb.append("<li class='page-item active'><span class='page-link'>" + i + "</span></li>");
				}else {
					sb.append("<li class='page-item'><a class='page-link' href='?p="+ i + "&s="+pageSize+"&b="+blockSize+"'>" + i + "</a></li>");
				}
			}
			// 다음은 끝페이지 번호가 전체페이지 번호 보다 적을때만 있다.
			if(endPage<totalPage) {
				sb.append("<li class='page-item'>");
				sb.append("<a class='page-link' href='?p="+(endPage+1)+"&s="+pageSize+"&b="+blockSize+"' aria-label='Next'>");
				sb.append("<span aria-hidden='true'>&raquo;</span>");
				sb.append("<span class='sr-only'>Next</span>");
				sb.append("</a>");
				sb.append("</li>");
			}
			sb.append("</ul>");
			return sb.toString();
		}
}

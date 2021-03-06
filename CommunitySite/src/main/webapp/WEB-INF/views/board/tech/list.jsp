<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</head>
<body>
	Tech List
	<div>
		<a href="${pageContext.request.contextPath }/tech/write">글쓰기</a>
	</div>
	
	<c:forEach items="${paging.list}" var="vo">
		<div class="my-3 p-3 bg-white rounded shadow-sm" onclick="location.href='${pageContext.request.contextPath}/tech/detail?idx=${vo.idx }'">
		  <div class="media text-muted pt-3">
		    <svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32"><title>Placeholder</title><rect fill="#007bff" width="100%" height="100%"/><text fill="#007bff" dy=".3em" x="50%" y="50%">32x32</text></svg>
		    <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
		      <strong class="d-block text-gray-dark">@${vo.writer }</strong>
		      ${vo.title }
		    </p>
		  </div>
		</div>
	</c:forEach>
	
	<c:if test="${paging.totalCount > 5}">
   		${paging.pageList2 }
   	</c:if>
	
</body>
</html>
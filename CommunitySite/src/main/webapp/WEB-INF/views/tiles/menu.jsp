<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">


</style>
</head>
<body>
	<div id="categoryList"></div>
	<div>
		<a href="https://github.com/kimnux/portfolio-01/issues" target="_blank">
			<img width="40px" height="40px" src="${pageContext.request.contextPath }/resources/image/git_hub.png" alt="이슈건의" />
		</a>
	</div>

<script type="text/javascript">
$(function() {
	$.ajax({
		type:"GET",
		url:"${pageContext.request.contextPath}/category/list", // 전송할 경로
		data: {} , // 전송할 키와 값
		success : function(data) {
			var html = "";
			for(var i = 0; i < data.length; i++) {
				console.log(data[i].categoryNm);
				html += "<div>";
				html += "  <a href='${pageContext.request.contextPath}"+data[i].url+"'>"+data[i].categoryNm+"</a>";
				html += "</div>";
			}
			$("#categoryList").html(html);
		},
		error : function(request, status, errorr) {
			console.log(errorr);
		}
	}); // end ajax
});
</script>
</body>
</html>
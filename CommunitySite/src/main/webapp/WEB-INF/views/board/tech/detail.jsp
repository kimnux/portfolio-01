<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/comm.js"></script>
</head>
<body>
	<!-- 본문 -->
	<div style="width: 70%; margin-left:20px; margin-top: 20px;">
		<div class="mb-2">
			<input type="text" class="form-control" disabled="disabled" value="${detail.title }" >
		</div>
		<div class="card" style="overflow:scroll; height: 550px;">
		  <div class="card-body">
		    <p class="card-text">${detail.content }</p>
		  </div>
		</div>
	</div>
	
	<!-- 댓글 -->
	<form action="${pageContext.request.contextPath }/tech/replyOk">
		<div class="form-floating" style="margin-left:20px; margin-top: 30px; width: 70%; border: 1px solid #ced4da;">
			<div style="height:30px; background-color: #ced4da;">답변</div>
			<div id="replyList"></div>
			<textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px"></textarea>
		</div>
	</form>
	
	<!-- 수정, 삭제 -->
	<div class="dropdown" style="margin-left:20px; width: 70%;" align="right">
		<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		 	<img alt="설정버튼" src="${pageContext.request.contextPath }/resources/image/settings-cogwheel-button.png" style="width: 20px;">
		</button>
		<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
			<button class="dropdown-item" onclick="sendPost('${pageContext.request.contextPath }/tech/edit',{idx:${detail.idx}});">수정</button>
			<button class="dropdown-item">삭제</button>
		</div>
	</div>
	
<script type="text/javascript">
$(function() {
	replyList();
});

function replyList() {
	$.ajax({
		data : {board_idx:"${detail.idx}"},
		type : "GET",
		url : "/tech/replyList",
		success : function(data) {
			console.log('success : ',data);
			var html = "";
			for(var i = 0; i < data.length; i++) {
				html += "<div>";
				html += data[i].replyContent;
				html += "</div>";
			console.log(data[i]);
			}
			$("#replyList").html(html);
		},
		error:function(request,status,error){
		    console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	   }
	});
}
	

</script>
</body>
</html>
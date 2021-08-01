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
	<!-- 수정, 삭제 -->
	<c:if test="${session.userId eq detail.writer }">
		<div class="dropdown" style="margin-left:20px; margin-top:10px; width: 70%;" align="right">
			<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			 	<img alt="설정버튼" src="${pageContext.request.contextPath }/resources/image/settings-cogwheel-button.png" style="width: 20px;">
			</button>
			<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
				<button class="dropdown-item" onclick="sendPost('${pageContext.request.contextPath }/tech/edit',{idx:${detail.idx}});">수정</button>
				<button class="dropdown-item">삭제</button>
			</div>
		</div>
	</c:if>
	
	<!-- 본문 -->
	<div style="width: 70%; margin-left:20px; margin-top: 10px;">
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
	<div class="form-floating" style="margin-left:20px; margin-top: 30px; width: 70%; border: 1px solid #ced4da;">
		<div style="height:30px; background-color: #ced4da;">답변</div>
		<div id="replyList"></div>
		<textarea class="form-control" id="reply_content" name="reply_content" style="height: 100px"></textarea>
	</div>
	<div style="margin-left:20px; margin-top: 10px; width: 70%;" align="right">
		<button type="button" class="btn btn-dark" onclick="formCheck()">댓글등록</button>
	</div>
	
	
	
<script type="text/javascript">
$(function() {
	replyList();
});

function ConvertSystemSourcetoHtml(str){
	str = str.replace(/</g,"&lt;");
	str = str.replace(/>/g,"&gt;");
	str = str.replace(/\"/g,"&quot;");
	str = str.replace(/\'/g,"&#39;");
	str = str.replace(/\n/g,"<br />");
	
	return str;
}

function formCheck() {
	var reply_content = $("#reply_content").val();
	reply_content = ConvertSystemSourcetoHtml(reply_content);
	$.ajax({
		data : {board_idx:"${detail.idx}", reply_content:reply_content},
		type : "POST",
		url : "${pageContext.request.contextPath }/tech/replyOk",
		success : function(data) {
			replyList();
			$("#reply_content").val("");
		},
		error:function(request,status,error){
		    console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	   }
	});
	
}

function replyList() {
	$.ajax({
		data : {board_idx:"${detail.idx}"},
		type : "GET",
		url : "${pageContext.request.contextPath }/tech/replyList",
		success : function(data) {
			console.log('success : ',data);
			var html = "";
			for(var i = 0; i < data.length; i++) {
				html += "<div>";
				html += "	<div style='float: left;'>";
				html += "		<p>";
				html += "@"+data[i].writer;
				html += "		</p>";
				html += "	</div>";
				html += "	<div align='center'>";
				html += "		<p>";
				html += data[i].reply_content;
				html += "		</p>";
				html += "	</div>";
				html += "</div>";
				html += "<hr>";
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
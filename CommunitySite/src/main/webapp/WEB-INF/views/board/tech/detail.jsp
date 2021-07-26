<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
상세화면
	<div style="width: 70%;">
		<div class="dropdown" align="right">
			<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			 	<img alt="설정버튼" src="${pageContext.request.contextPath }/resources/image/settings-cogwheel-button.png" style="width: 20px;">
			</button>
			<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
				<button class="dropdown-item" onclick="sendPost('${pageContext.request.contextPath }/tech/edit',{idx:${detail.idx}});">수정</button>
				<button class="dropdown-item">삭제</button>
			</div>
		</div>
		<div class="mb-3">
			<input type="text" class="form-control" disabled="disabled" value="${detail.title }" >
		</div>
		<div class="card" style="overflow:scroll; height: 550px;">
		  <div class="card-body">
		    <p class="card-text">${detail.content }</p>
		  </div>
		</div>
	</div>
</body>
</html>
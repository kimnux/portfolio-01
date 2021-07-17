<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원가입</h2>
	<div>
		<div>
			<span>ID</span>
			<input type="text" id="userId" name="userId" />
		</div>
		<div>
			<span>PASSWORD</span>
			<input type="password" id="password" name="password" />
		</div>
		<div>
			<span>닉네임</span>
			<input type="text" id="nickName" name="nickName" />
		</div>
		<button onclick="joinForm();">회원가입</button>
	</div>
	
<script>
function joinForm() {
	var userId = $('#userId').val();
	var password = $('#password').val();
	var nickName = $('#nickName').val();
	
	$.ajax({
		type:"POST",
		url:"${pageContext.request.contextPath}/member/joinOk", // 전송할 경로
		data: {"userId":userId, "password":password, "nickName":nickName} , // 전송할 키와 값
		success : function(data) {
			if(data === 1) {
				location.href="${pageContext.request.contextPath}/";
			}else {
				return ;
			}
		},
		error : function(request, status, errorr) {
			console.log(errorr);
		}
	}); // end ajax	
}
</script>
</body>
</html>
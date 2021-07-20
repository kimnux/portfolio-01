<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	<div>
		<div>
			<label>ID</label>
			<input type="text" id="userId" name="userId" value="">		
		</div>
		<div>
			<label>password</label>
			<input type="password" id="password" name="password" value="">
		</div>
		<div>
			<span id="failText"></span>
		</div>
		<div>
			<input type="button" onclick="loginCheck();" value="로그인">
		</div>
	</div>
	
<script>

function loginCheck() {
	var userId = $('#userId').val();
	var password = $('#password').val();

	$.ajax({
		type:"POST",
		url:"${pageContext.request.contextPath}/member/loginOk", // 전송할 경로
		data: {"userId":userId , "password":password} , // 전송할 키와 값
		success : function(data) {
			if(data === 1) {
				alert("안녕하세요 반갑습니다.");
				location.href="${pageContext.request.contextPath}/";
			}else if( data === -1 ) {
				$('#failText').html('아이디/비밀번호를 환인해주세요.');
				$('#failText').css('color','red').css('font-weight', 'bold');
			} else if( data === 0 ){
				alert('비밀번호가 5회이상 틀렸습니다.\n관리자에게 문의하세요.');
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
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
	
	var regExpPassword = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^*+=-]).{6,16}$/;
	var regExpNickName = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]{2,10}$/;

	if( userId.length < 7 ) {
			alert('아이디는 7자리 이상이어야 합니다.');
	}else if( !regExpPassword.test(password) ) {
		alert('비밀번호는 영문,숫자,특수문자 포함 및 6~16자리여야 합니다.');
	}else if( !regExpNickName.test(nickName) ){
		alert("닉네임은 한글, 영문, 숫자만 가능하며 2-10자리 가능합니다.");
	}else {
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
	
		
}
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div>
		<div>
			<label>ID</label>
			<input type="text" id="userId" name="userId" value="" onkeyup="enter()">		
		</div>
		<div>
			<label>password</label>
			<input type="password" id="password" name="password" value="" onkeyup="enter()">
		</div>
		<div>
			<span id="failText"></span>
		</div>
		<div>
			<input type="button" onclick="loginCheck();" value="로그인">
		</div>
	</div>
	<a onclick="kakaoLogin()">
		<img width="30px;" height="30px;" alt="카카오 로그인" src="${pageContext.request.contextPath }/resources/image/kakao_logo.png">
	</a>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
//카카오로그인
function kakaoLogin() {

  $.ajax({
      url: '${pageContext.request.contextPath}/getKakaoAuthUrl',
      type: 'get',
      async: false,
      dataType: 'text',
      success: function (res) {
          location.href = res;
      }
  });

}

$(document).ready(function() {

	var kakaoInfo = '${kakaoInfo}';
	
	if(kakaoInfo != ""){
	    var data = JSON.parse(kakaoInfo);
	
	    alert("카카오로그인 성공 \n accessToken : " + data['accessToken']);
	    alert(
	    "user : \n" + "email : "
	    + data['email']  
	    + "\n nickname : " 
	    + data['nickname']);
	}
});  

</script>
	
<script>

function enter() {
	if(window.event.keyCode == 13) {
		loginCheck();
	}
}

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